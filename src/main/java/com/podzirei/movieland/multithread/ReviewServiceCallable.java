package com.podzirei.movieland.multithread;

import com.podzirei.movieland.entity.Review;
import com.podzirei.movieland.exception.ReviewNotFoundException;
import com.podzirei.movieland.repository.JpaReviewRepository;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@Getter
public class ReviewServiceCallable implements Callable<String> {

    private static final long WAITING_TASK_TIME = 5000;
    private List<Review> reviews = new ArrayList<>();
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
//        while (System.currentTimeMillis() < end) {
            reviews = jpaReviewRepository.findReviewsByMovieNative(movieId)
                    .orElseThrow(() -> new ReviewNotFoundException(movieId));
//            return Thread.currentThread().getName();
//        }
//        Thread.currentThread().interrupt();

        return Thread.currentThread().getName();
    }
}
