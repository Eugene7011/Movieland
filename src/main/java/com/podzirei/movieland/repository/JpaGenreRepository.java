package com.podzirei.movieland.repository;

import com.podzirei.movieland.entity.Genre;
import com.podzirei.movieland.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaGenreRepository extends JpaRepository<Genre, Integer>, GenreRepository {
    List<Genre> findGenresByMoviesContains(Movie movie);

}
