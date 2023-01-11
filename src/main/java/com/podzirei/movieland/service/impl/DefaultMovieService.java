package com.podzirei.movieland.service.impl;

import com.podzirei.movieland.currency.CurrencyDataDto;
import com.podzirei.movieland.currency.CurrencyRateParser;
import com.podzirei.movieland.dto.MovieDto;
import com.podzirei.movieland.entity.Country;
import com.podzirei.movieland.entity.Genre;
import com.podzirei.movieland.entity.Movie;
import com.podzirei.movieland.entity.Review;
import com.podzirei.movieland.exception.CurrencyNotFoundException;
import com.podzirei.movieland.exception.MovieNotFoundException;
import com.podzirei.movieland.exception.ReviewNotFoundException;
import com.podzirei.movieland.mapper.CountryMapper;
import com.podzirei.movieland.mapper.GenreMapper;
import com.podzirei.movieland.mapper.MovieMapper;
import com.podzirei.movieland.mapper.ReviewMapper;
import com.podzirei.movieland.multithread.CountryServiceRunnable;
import com.podzirei.movieland.multithread.GenreServiceRunnable;
import com.podzirei.movieland.multithread.ReviewServiceCallable;
import com.podzirei.movieland.repository.JpaCountryRepository;
import com.podzirei.movieland.repository.JpaGenreRepository;
import com.podzirei.movieland.repository.JpaMovieRepository;
import com.podzirei.movieland.repository.JpaReviewRepository;
import com.podzirei.movieland.repository.MovieRepository;
import com.podzirei.movieland.request.MovieRequest;
import com.podzirei.movieland.request.MovieUpdateRequest;
import com.podzirei.movieland.response.MovieResponse;
import com.podzirei.movieland.response.MovieResultResponse;
import com.podzirei.movieland.service.MovieService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
@Getter
@RequiredArgsConstructor
public class DefaultMovieService implements MovieService {

    public static List<Genre> genres = new CopyOnWriteArrayList<>();
    public static List<Country> countries = new CopyOnWriteArrayList<>();
    public static List<Review> reviews = new CopyOnWriteArrayList<>();
    @Value("${movies.random.count:3}")
    private int randomNumber;
    private final MovieMapper movieMapper;
    private final GenreMapper genreMapper;
    private final ReviewMapper reviewMapper;
    private final CountryMapper countryMapper;
    private final MovieRepository movieRepository;
    private final JpaMovieRepository jpaMovieRepository;
    private final JpaGenreRepository jpaGenreRepository;
    private final JpaCountryRepository jpaCountryRepository;
    private final JpaReviewRepository jpaReviewRepository;
    private final CurrencyRateParser currencyRateParser;

    @Override
    @Transactional(readOnly = true)
    public List<MovieResponse> findAll() {
        return movieMapper.toMovieResponses(movieRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieResponse> findAll(MovieRequest movieRequest) {
        return movieMapper.toMovieResponses(movieRepository.findAll(movieRequest));
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieResponse> findRandom() {
        return movieMapper.toMovieResponses(movieRepository.getRandom(randomNumber));
    }

    @SneakyThrows
    @Override
    @Transactional(readOnly = true)
    public MovieResultResponse findByMovieId(int movieId) {
        Movie movie = jpaMovieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException(movieId));

        ExecutorService executorService = Executors.newCachedThreadPool();

        GenreServiceRunnable genreServiceRunnable = new GenreServiceRunnable(jpaGenreRepository, movie);
        executorService.execute(genreServiceRunnable);
//        List<Genre> genres = genreServiceRunnable.getGenres();

        CountryServiceRunnable countryServiceRunnable = new CountryServiceRunnable(jpaCountryRepository, movie);
        executorService.execute(countryServiceRunnable);
//        List<Country> countries = countryServiceRunnable.getCountries();

        ReviewServiceCallable reviewServiceCallable = new ReviewServiceCallable(jpaReviewRepository, movieId);
        Future<String> future = executorService.submit(reviewServiceCallable);
        future.get();
//        List<Review> reviews = reviewServiceCallable.getReviews();
//        try {
//            List<Review> call = reviewServiceCallable.call();
//        } catch (InterruptedException | ExecutionException e | ) {
//            e.printStackTrace();
//        }


//        List<Genre> genres = jpaGenreRepository.findGenresByMoviesContains(movie);
//        List<Country> countries = jpaCountryRepository.findCountriesByMoviesContains(movie);
//        List<Review> reviews = jpaReviewRepository.findReviewsByMovieNative(movieId)
//                .orElseThrow(() -> new ReviewNotFoundException(movieId));

        MovieResultResponse movieResultResponse = setMovieResultDto(movie);

        movieResultResponse.setGenres(genreMapper.genresToGenresDtos(new HashSet<>(genres)));
        movieResultResponse.setCountries(countryMapper.toCountryDtos(new HashSet<>(countries)));
        movieResultResponse.setReviews(reviewMapper.toReviewResponses(reviews));

        return movieResultResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public MovieResultResponse findByMovieId(int movieId, String currency) {
        CurrencyDataDto currencyData = currencyRateParser.getAllRates()
                .stream()
                .filter(dataDto -> dataDto.getCc().equalsIgnoreCase(currency))
                .findFirst()
                .orElseThrow(() -> new CurrencyNotFoundException(currency));

        MovieResultResponse movieResultResponse = findByMovieId(movieId);
        movieResultResponse.setPrice(currencyData.getRate() * movieResultResponse.getPrice());
        return movieResultResponse;
    }

    @Override
    public MovieResultResponse add(MovieDto movieDto) {
        Movie movie = movieMapper.toMovie(movieDto);
        setGenresAndCountries(movie, movieDto.getGenres(), movieDto.getCountries());

        return setMovieResultDto(jpaMovieRepository.save(movie));
    }

    @Override
    public MovieResultResponse update(int movieId, MovieUpdateRequest movieUpdateRequest) {
        Movie movie = jpaMovieRepository.findById(movieId)
                .map(mov -> movieMapper.update(mov, movieUpdateRequest))
                .orElseThrow(() -> new MovieNotFoundException(movieId));
        setGenresAndCountries(movie, movieUpdateRequest.getGenres(), movieUpdateRequest.getCountries());

        return setMovieResultDto(jpaMovieRepository.save(movie));
    }

    private void setGenresAndCountries(Movie movie, Set<Integer> genres, Set<Integer> countries) {
        Set<Genre> genresByIds = new HashSet<>(jpaGenreRepository.findAllById(genres));
        genresByIds.forEach(genre -> genre.getMovies().clear());
        Set<Country> countriesById = new HashSet<>(jpaCountryRepository.findAllById(countries));
        countriesById.forEach(country -> country.getMovies().clear());

        movie.setCountries(countriesById);
        movie.setGenres(genresByIds);
    }

    private MovieResultResponse setMovieResultDto(Movie movie) {
        MovieResultResponse movieResultResponse = new MovieResultResponse();
        movieResultResponse.setId(movie.getId());
        movieResultResponse.setNameRussian(movie.getNameRussian());
        movieResultResponse.setNameNative(movie.getNameNative());
        SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
        movieResultResponse.setYearOfRelease(formatYear.format(movie.getYearOfRelease()));
        movieResultResponse.setDescription(movie.getDescription());
        movieResultResponse.setRating(movie.getRating());
        movieResultResponse.setPrice(movie.getPrice());
        movieResultResponse.setPicturePath(movie.getPicturePath());
        movieResultResponse.setGenres(genreMapper.genresToGenresDtos(movie.getGenres()));
        movieResultResponse.setCountries(countryMapper.toCountryDtos(movie.getCountries()));
        List<Review> reviews = jpaReviewRepository.findReviewsByMovieNative(movie.getId())
                .orElseThrow(() -> new ReviewNotFoundException(movie.getId()));
        movieResultResponse.setReviews(reviewMapper.toReviewResponses(reviews));

        return movieResultResponse;
    }
}
