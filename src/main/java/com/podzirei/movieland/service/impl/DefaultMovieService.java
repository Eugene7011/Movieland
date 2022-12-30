package com.podzirei.movieland.service.impl;

import com.podzirei.movieland.dto.MovieDto;
import com.podzirei.movieland.mapper.MovieMapper;
import com.podzirei.movieland.repository.MovieRepository;
import com.podzirei.movieland.service.MovieService;
import com.podzirei.movieland.web.controller.movie.MovieRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultMovieService implements MovieService {

    @Value("${movies.random.count:3}")
    private int randomNumber;
    private final MovieMapper movieMapper;
    private final MovieRepository movieRepository;
//    private final GenreRepository genreRepository;

    @Override
    public List<MovieDto> findAll() {
        return movieMapper.moviesToMovieDtos(movieRepository.findAll());
    }

    @Override
    public List<MovieDto> findAll(MovieRequest movieRequest) {
        return movieMapper.moviesToMovieDtos(movieRepository.findAll(movieRequest));
    }

    @Override
    public List<MovieDto> findRandom() {
        List<MovieDto> allMovies = movieMapper.moviesToMovieDtos(movieRepository.getRandom(randomNumber));
        return allMovies;
    }

    @Override
    public List<MovieDto> findByMovieId(int movieId) {
        return null;
    }
//
//    @Override
//    public List<MovieDto> findAllByPriceSorted(SortDirection sortType) {
//        if (SortDirection.DESC.equals(sortType)) {
//            return movieMapper.moviesToMovieDtos(movieRepository.findAll(Sort.by("price").descending()));
//        }
//        return movieMapper.moviesToMovieDtos(movieRepository.findAll(Sort.by("price")));
//    }
//
//    @Override
//    public List<MovieDto> findAllByRatingSorted(SortDirection sortType) {
//        if (SortDirection.DESC.equals(sortType)) {
//            return movieMapper.moviesToMovieDtos(movieRepository.findAll(Sort.by("rating").descending()));
//        }
//        return movieMapper.moviesToMovieDtos(movieRepository.findAll(Sort.by("rating")));
//    }
//
//    @Override
//    public List<MovieDto> findByGenreId(int genreId) {
//        Genre genre = findGenreById(genreId);
//        return movieMapper.moviesToMovieDtos(movieRepository.findMoviesByGenresIn(Set.of(genre)));
//    }
//
//    @Override
//    public List<MovieDto> findByGenreIdOrderByRating(int genreId, SortDirection sortType) {
//        Genre genre = findGenreById(genreId);
//        if (SortDirection.DESC.equals(sortType)) {
//            return movieMapper.moviesToMovieDtos(movieRepository.findMoviesByGenresInOrderByRatingDesc(Set.of(genre)));
//        }
//        return movieMapper.moviesToMovieDtos(movieRepository.findMoviesByGenresInOrderByRatingAsc(Set.of(genre)));
//    }
//
//    @Override
//    public List<MovieDto> findByGenreIdOrderByPrice(int genreId, SortDirection sortType) {
//        Genre genre = findGenreById(genreId);
//        if (SortDirection.DESC.equals(sortType)) {
//            return movieMapper.moviesToMovieDtos(movieRepository.findMoviesByGenresInOrderByPriceDesc(Set.of(genre)));
//        }
//        return movieMapper.moviesToMovieDtos(movieRepository.findMoviesByGenresInOrderByPriceAsc(Set.of(genre)));
//    }
//
//    private Genre findGenreById(int genreId) {
//        return genreRepository.findById(genreId)
//                .orElseThrow(() -> new IllegalArgumentException("No genre for id: " + genreId));
//    }
}
