package com.podzirei.movieland.multithread;

import com.podzirei.movieland.entity.Review;
import com.podzirei.movieland.exception.ReviewNotFoundException;
import com.podzirei.movieland.repository.JpaReviewRepository;
import com.podzirei.movieland.service.impl.DefaultMovieService;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@Getter
public class ReviewServiceCallable implements Callable<String> {

    private static final long WAITING_TASK_TIME = 5000;
    private final JpaReviewRepository jpaReviewRepository;
    private final int movieId;

    public ReviewServiceCallable(JpaReviewRepository jpaReviewRepository, int movieId) {
        this.jpaReviewRepository = jpaReviewRepository;
        this.movieId = movieId;
    }

    @Override
    public String call() throws Exception {
        long start = System.currentTimeMillis();
        long end = start + WAITING_TASK_TIME;
        while (System.currentTimeMillis() < end || DefaultMovieService.reviews.isEmpty()) {
            DefaultMovieService.reviews = jpaReviewRepository.findReviewsByMovieNative(movieId)
                    .orElseThrow(() -> new ReviewNotFoundException(movieId));
            Thread.currentThread().interrupt();
        }

        return Thread.currentThread().getName();
    }
}
