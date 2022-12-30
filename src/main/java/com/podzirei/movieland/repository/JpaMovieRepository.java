package com.podzirei.movieland.repository;

import com.podzirei.movieland.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMovieRepository extends JpaRepository<Movie, Integer> {

}