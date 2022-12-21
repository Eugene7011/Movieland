package com.podzirei.movieland.repository;

import com.podzirei.movieland.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    public List<Genre> findAll();

    public Optional<Genre> findById(int id);
}
