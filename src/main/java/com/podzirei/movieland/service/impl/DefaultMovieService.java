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
        return movieMapper.moviesToMovieDtos(movieRepository.getRandom(randomNumber));
    }

    @Override
    public List<MovieDto> findByMovieId(int movieId) {
        return null;
    }
}
