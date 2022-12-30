package com.podzirei.movieland.repository;

import com.podzirei.movieland.entity.Movie;
import com.podzirei.movieland.web.controller.movie.MovieRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository {

    List<Movie> findAll();
    List<Movie> findAll(MovieRequest movieRequest);
    List<Movie> getRandom(int randomNumber);


//    List<Movie> findMoviesByGenresIn(Set<Genre> genre_id);
//
//    List<Movie> findMoviesByGenresInOrderByRatingAsc(Set<Genre> genre_id);
//
//    List<Movie> findMoviesByGenresInOrderByRatingDesc(Set<Genre> genre_id);
//
//    List<Movie> findMoviesByGenresInOrderByPriceAsc(Set<Genre> genre_id);
//
//    List<Movie> findMoviesByGenresInOrderByPriceDesc(Set<Genre> genre_id);
}
