package com.podzirei.movieland.service.impl;

import com.podzirei.movieland.dto.GenreDto;
import com.podzirei.movieland.mapper.GenreMapper;
import com.podzirei.movieland.repository.GenreRepository;
import com.podzirei.movieland.service.GenreService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultGenreService implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public GenreDto findById(int id) {
        return genreMapper.movieToMovieDto(genreRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No car for id: " + id)));
    }

    @Override
    @Cacheable("genres")
//    @Scheduled(fixedRateString = "${caching.spring.genres}")
    public List<GenreDto> findAll() {
        return genreMapper.genresToGenresDtos(genreRepository.findAll());
    }

    @CacheEvict(value = "genres", allEntries = true)
    @Scheduled(fixedRateString = "${caching.spring.genres}")
    public void emptyGenresCache() {
        log.debug("emptying genres cache");
    }
}
