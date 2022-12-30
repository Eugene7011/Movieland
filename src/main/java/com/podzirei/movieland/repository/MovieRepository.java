package com.podzirei.movieland.repository;

import com.podzirei.movieland.entity.Movie;
import com.podzirei.movieland.web.controller.movie.MovieRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository {

    List<Movie> findAll();
    List<Movie> findAll(MovieRequest movieRequest);
    List<Movie> getRandom(int randomNumber);

    Optional<Movie> findById(int movieId);
}
