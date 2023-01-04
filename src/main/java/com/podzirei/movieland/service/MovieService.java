package com.podzirei.movieland.service;

import com.podzirei.movieland.dto.MovieDto;
import com.podzirei.movieland.dto.MovieResultDto;
import com.podzirei.movieland.web.controller.movie.MovieRequest;

import java.util.List;

public interface MovieService {

    List<MovieDto> findAll();
    List<MovieDto> findAll(MovieRequest movieRequest);
    List<MovieDto> findRandom();
    MovieResultDto findByMovieId(int movieId);
}
