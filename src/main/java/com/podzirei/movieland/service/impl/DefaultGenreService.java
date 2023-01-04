package com.podzirei.movieland.service.impl;

import com.podzirei.movieland.dto.GenreDto;
import com.podzirei.movieland.mapper.GenreMapper;
import com.podzirei.movieland.repository.GenreRepository;
import com.podzirei.movieland.service.GenreService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultGenreService implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    @Transactional(readOnly = true)
    public List<GenreDto> findAll() {
        return genreMapper.genresToGenresDtos(genreRepository.findAll());
    }
}
