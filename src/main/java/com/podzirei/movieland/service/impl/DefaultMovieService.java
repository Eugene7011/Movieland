package com.podzirei.movieland.service.impl;

import com.podzirei.movieland.dto.MovieDto;
import com.podzirei.movieland.mapper.MovieMapper;
import com.podzirei.movieland.repository.MovieRepository;
import com.podzirei.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DefaultMovieService implements MovieService {

    private final MovieMapper movieMapper;
    private final MovieRepository movieRepository;

    @Override
    public List<MovieDto> findAll() {
        return movieMapper.moviesToMovieDtos(movieRepository.findAll());
    }

    @Override
    public List<MovieDto> findAllByPriceSorted(String sortType) {
        validateSortType(sortType);

        if ("desc".equals(sortType)) {
            return movieMapper.moviesToMovieDtos(movieRepository.findAll(Sort.by("price").descending()));
        }
        return movieMapper.moviesToMovieDtos(movieRepository.findAll(Sort.by("price")));
    }

    @Override
    public List<MovieDto> findAllByRatingSorted(String sortType) {
        validateSortType(sortType);

        if ("desc".equals(sortType)) {
            return movieMapper.moviesToMovieDtos(movieRepository.findAll(Sort.by("rating").descending()));
        }
        return movieMapper.moviesToMovieDtos(movieRepository.findAll(Sort.by("rating")));
    }

    @Override
    public List<MovieDto> findThreeRandom() {
        List<MovieDto> allMovies = movieMapper.moviesToMovieDtos(movieRepository.findAll());
        if (allMovies.size() < 3) {
            return allMovies;
        }

        List<MovieDto> randomMovies = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int randomIndex = random.nextInt(allMovies.size());
            MovieDto randomMovie = allMovies.get(randomIndex);
            randomMovies.add(randomMovie);
            allMovies.remove(randomMovie);
        }
        return randomMovies;
    }

    private void validateSortType(String sortType) {
        if (!"asc".equalsIgnoreCase(sortType) && !"desc".equalsIgnoreCase(sortType)) {
            throw new IllegalArgumentException("Sort parameter should be asc or desc");
        }
    }
}
