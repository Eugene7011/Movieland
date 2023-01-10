package com.podzirei.movieland.repository;

import com.podzirei.movieland.entity.Genre;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

//@Primary
@Cache
@RequiredArgsConstructor
@Slf4j
public class CachedGenreRepository implements GenreRepository {
    private volatile List<Genre> cachedGenres;
    private final JpaGenreRepository defaultGenreRepository;

    @Override
    public List<Genre> findAll() {
        return new CopyOnWriteArrayList<>(cachedGenres);
    }

    @Scheduled(fixedRate = 1400000, initialDelay = 1400000)
//    @PostConstruct
    @Transactional(readOnly = true)
    public void updateCache() {
        cachedGenres = defaultGenreRepository.findAll();
        log.info("genres cache updated");
    }
}

@Component
@Retention(RetentionPolicy.RUNTIME)
@interface Cache {

}
