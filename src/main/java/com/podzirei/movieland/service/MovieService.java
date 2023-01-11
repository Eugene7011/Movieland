package com.podzirei.movieland.service;

import com.podzirei.movieland.dto.MovieDto;
import com.podzirei.movieland.response.MovieResultResponse;
import com.podzirei.movieland.request.MovieRequest;
import com.podzirei.movieland.response.MovieResponse;
import com.podzirei.movieland.request.MovieUpdateRequest;

import java.util.List;

public interface MovieService {

    List<MovieResponse> findAll();

    List<MovieResponse> findAll(MovieRequest movieRequest);

    List<MovieResponse> findRandom();

    MovieResultResponse findByMovieId(int movieId);

    MovieResultResponse findByMovieId(int movieId, String currency);

    MovieResultResponse add(MovieDto movieDto);

    MovieResultResponse update(int movieId, MovieUpdateRequest movieUpdateRequest);
}
