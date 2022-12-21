package com.podzirei.movieland.repository;

import com.podzirei.movieland.entity.Genre;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@RequiredArgsConstructor
@Slf4j
public class CachedGenreRepository implements GenreRepository {
    private volatile List<Genre> cachedGenres;
    private final DefaultGenreRepository defaultGenreRepository;

    @Override
    public List<Genre> findAll() {
        return new CopyOnWriteArrayList<>(cachedGenres);
    }

    @Override
    public Optional<Genre> findById(int id) {
        return defaultGenreRepository.findById(id);
    }

    @Scheduled(fixedRate = 1400000, initialDelay = 1400000)
//    @PostConstruct
    @Transactional(readOnly = true)
    public void updateCache() {
        cachedGenres = defaultGenreRepository.findAll();
        log.info("genres cache updated");
    }
}
