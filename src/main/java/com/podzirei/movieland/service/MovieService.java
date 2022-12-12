package com.podzirei.movieland.service;

import com.podzirei.movieland.dto.MovieDto;

import java.util.List;

public interface MovieService {

    List<MovieDto> findAll();
    List<MovieDto> findThreeRandom();
}
