package com.podzirei.movieland.repository;

import com.podzirei.movieland.entity.Genre;

import java.util.List;

public interface GenreRepository {
    List<Genre> findAll();
}
