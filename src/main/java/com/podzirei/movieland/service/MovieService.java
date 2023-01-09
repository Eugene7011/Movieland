package com.podzirei.movieland.service;

import com.podzirei.movieland.dto.MovieDto;
import com.podzirei.movieland.dto.MovieResultDto;
import com.podzirei.movieland.web.controller.movie.MovieRequest;
import com.podzirei.movieland.web.controller.movie.MovieResponse;
import com.podzirei.movieland.web.controller.movie.MovieUpdateRequest;

import java.util.List;

public interface MovieService {

    List<MovieResponse> findAll();

    List<MovieResponse> findAll(MovieRequest movieRequest);

    List<MovieResponse> findRandom();

    MovieResultDto findByMovieId(int movieId);

    MovieResultDto findByMovieId(int movieId, String currency);

    MovieResultDto add(MovieDto movieDto);

    MovieResultDto update(int movieId, MovieUpdateRequest movieUpdateRequest);
}
