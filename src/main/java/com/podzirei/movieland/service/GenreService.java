package com.podzirei.movieland.service;

import com.podzirei.movieland.dto.GenreDto;

import java.util.Set;

public interface GenreService {
    Set<GenreDto> findAll();
}
