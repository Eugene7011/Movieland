package com.podzirei.movieland.repository;

import com.podzirei.movieland.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaGenreRepository extends JpaRepository<Genre, Integer>, GenreRepository {

}
