package com.podzirei.movieland.multithread;

import com.podzirei.movieland.entity.Genre;
import com.podzirei.movieland.entity.Movie;
import com.podzirei.movieland.repository.JpaGenreRepository;
import com.podzirei.movieland.service.impl.DefaultMovieService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class GenreServiceRunnable implements Runnable {

    private static final long WAITING_TASK_TIME = 5000;
    private final JpaGenreRepository jpaGenreRepository;
    private final Movie movie;

    public GenreServiceRunnable(JpaGenreRepository jpaGenreRepository, Movie movie) {
        this.jpaGenreRepository = jpaGenreRepository;
        this.movie = movie;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        long end = start + WAITING_TASK_TIME;
        while (System.currentTimeMillis() < end || DefaultMovieService.genres.isEmpty()) {
            DefaultMovieService.genres = jpaGenreRepository.findGenresByMoviesContains(movie);
            Thread.currentThread().interrupt();
        }
    }
}
