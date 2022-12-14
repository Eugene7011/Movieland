package com.podzirei.movieland.repository;

import com.podzirei.movieland.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
@AllArgsConstructor
@Slf4j
public class GenreRepository {
    private final CopyOnWriteArrayList<Genre> cachedGenres = new CopyOnWriteArrayList<>();
    private final DefaultGenreRepository defaultGenreRepository;

    public List<Genre> findAll() {
        return cachedGenres;
    }

    public Optional<Genre> findById(int id) {
        return defaultGenreRepository.findById(id);
    }

    @Scheduled(fixedRate = 1400000)
    public void updateCache() {
        cachedGenres.clear();
        cachedGenres.addAll(defaultGenreRepository.findAll());
        log.info("genres cache updated");
    }
}
