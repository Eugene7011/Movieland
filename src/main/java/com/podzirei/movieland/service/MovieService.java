package com.podzirei.movieland.service;

import com.podzirei.movieland.dto.MovieDto;

import java.util.List;

public interface MovieService {

    List<MovieDto> findAll();
    List<MovieDto> findThreeRandom();

    List<MovieDto> findAllByRatingSorted(String sortType);

    List<MovieDto> findAllByPriceSorted(String sortType);

    List<MovieDto> findByGenreId(int genreId);

    List<MovieDto> findByGenreIdOrderByRating(int genreId, String sortType);

    List<MovieDto> findByGenreIdOrderByPrice(int genreId, String sortType);
}
