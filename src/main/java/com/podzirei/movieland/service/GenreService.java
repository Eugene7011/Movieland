package com.podzirei.movieland.service;

import com.podzirei.movieland.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> findAll();
}
