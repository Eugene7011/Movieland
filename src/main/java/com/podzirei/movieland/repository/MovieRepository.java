package com.podzirei.movieland.repository;

import com.podzirei.movieland.entity.Genre;
import com.podzirei.movieland.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findMoviesByGenresIn(Set<Genre> genre_id);

    List<Movie> findMoviesByGenresInOrderByRatingAsc(Set<Genre> genre_id);

    List<Movie> findMoviesByGenresInOrderByRatingDesc(Set<Genre> genre_id);

    List<Movie> findMoviesByGenresInOrderByPriceAsc(Set<Genre> genre_id);

    List<Movie> findMoviesByGenresInOrderByPriceDesc(Set<Genre> genre_id);
}
