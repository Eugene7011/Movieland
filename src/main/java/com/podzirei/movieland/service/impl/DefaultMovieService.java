package com.podzirei.movieland.service.impl;

import com.podzirei.movieland.currency.CurrencyDataDto;
import com.podzirei.movieland.currency.CurrencyRateParser;
import com.podzirei.movieland.dto.MovieDto;
import com.podzirei.movieland.dto.MovieResultDto;
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
import com.podzirei.movieland.repository.JpaCountryRepository;
import com.podzirei.movieland.repository.JpaGenreRepository;
import com.podzirei.movieland.repository.JpaMovieRepository;
import com.podzirei.movieland.repository.JpaReviewRepository;
import com.podzirei.movieland.repository.MovieRepository;
import com.podzirei.movieland.service.MovieService;
import com.podzirei.movieland.web.controller.movie.MovieRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultMovieService implements MovieService {

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
    public List<MovieDto> findAll() {
        return movieMapper.toMovieDtos(movieRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> findAll(MovieRequest movieRequest) {
        return movieMapper.toMovieDtos(movieRepository.findAll(movieRequest));
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDto> findRandom() {
        return movieMapper.toMovieDtos(movieRepository.getRandom(randomNumber));
    }

    @Override
    @Transactional(readOnly = true)
    public MovieResultDto findByMovieId(int movieId) {
        Movie movie = jpaMovieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException(movieId));
        List<Genre> genres = jpaGenreRepository.findGenresByMoviesContains(movie);
        List<Country> countries = jpaCountryRepository.findCountriesByMoviesContains(movie);
        List<Review> reviews = jpaReviewRepository.findReviewsByMovieNative(movieId)
                .orElseThrow(() -> new ReviewNotFoundException(movieId));

        MovieResultDto movieResultDto = setMovieResultDto(movie);

        movieResultDto.setGenres(genreMapper.genresToGenresDtos(genres));
        movieResultDto.setCountries(countryMapper.toCountryDtos(countries));
        movieResultDto.setReviews(reviewMapper.toReviewResponses(reviews));

        return movieResultDto;
    }

    @Override
    @Transactional(readOnly = true)
    public MovieResultDto findByMovieId(int movieId, String currency) {
        CurrencyDataDto currencyData = currencyRateParser.getAllRates()
                .stream()
                .filter(dataDto -> dataDto.getCc().equalsIgnoreCase(currency))
                .findFirst()
                .orElseThrow(() -> new CurrencyNotFoundException(currency));

        MovieResultDto movieResultDto = findByMovieId(movieId);
        movieResultDto.setPrice(currencyData.getRate() * movieResultDto.getPrice());
        return movieResultDto;
    }

    private MovieResultDto setMovieResultDto(Movie movie) {
        MovieResultDto movieResultDto = new MovieResultDto();
        movieResultDto.setId(movie.getId());
        movieResultDto.setNameRussian(movie.getNameRussian());
        movieResultDto.setNameNative(movie.getNameNative());
        SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
        movieResultDto.setYearOfRelease(formatYear.format(movie.getYearOfRelease()));
        movieResultDto.setDescription(movie.getDescription());
        movieResultDto.setRating(movie.getRating());
        movieResultDto.setPrice(movie.getPrice());
        movieResultDto.setPicturePath(movie.getPicturePath());

        return movieResultDto;
    }
}
