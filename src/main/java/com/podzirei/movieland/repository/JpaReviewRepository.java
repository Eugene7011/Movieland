package com.podzirei.movieland.repository;

import com.podzirei.movieland.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JpaReviewRepository extends JpaRepository<Review, Integer> {

    @Query(value = "select r1_0.id,r1_0.description,r1_0.movie_id,r1_0.user_id from Review r1_0 where r1_0.movie_id=?1",
            nativeQuery = true)
    Optional<List<Review>> findReviewsByMovieNative(int id);
}