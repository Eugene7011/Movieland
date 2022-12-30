package com.podzirei.movieland.service;

import com.podzirei.movieland.dto.MovieDto;
import com.podzirei.movieland.web.controller.movie.MovieRequest;

import java.util.List;

public interface MovieService {

    List<MovieDto> findAll();
    List<MovieDto> findAll(MovieRequest movieRequest);
    List<MovieDto> findRandom();

    List<MovieDto> findByMovieId(int movieId);
//
//    List<MovieDto> findAllByRatingSorted(SortDirection sortType);
//
//    List<MovieDto> findAllByPriceSorted(SortDirection sortType);
//
//    List<MovieDto> findByGenreId(int genreId);
//
//    List<MovieDto> findByGenreIdOrderByRating(int genreId, SortDirection sortType);
//
//    List<MovieDto> findByGenreIdOrderByPrice(int genreId, SortDirection sortType);
}
