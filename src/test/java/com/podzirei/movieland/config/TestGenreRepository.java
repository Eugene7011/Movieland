package com.podzirei.movieland.config;

import com.podzirei.movieland.entity.Genre;
import com.podzirei.movieland.repository.GenreRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class TestGenreRepository implements GenreRepository {
    @Override
    public List<Genre> findAll() {
        return List.of(new Genre(1, "драма"),
                new Genre(2, "криминал"));
    }
}
