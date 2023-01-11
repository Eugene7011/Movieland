package com.podzirei.movieland.mapper;

import com.podzirei.movieland.dto.ReviewDto;
import com.podzirei.movieland.entity.Movie;
import com.podzirei.movieland.entity.Review;
import com.podzirei.movieland.entity.User;
import com.podzirei.movieland.response.ReviewResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {ReviewMapperImpl.class, UserMapperImpl.class})
class ReviewMapperTest {

    @Autowired
    private ReviewMapper reviewMapper;

    @Test
    @DisplayName("when Map ReviewDto then Return Review")
    void whenMapReviewDto_thenReturnReview() {
        ReviewDto reviewDto = ReviewDto.builder()
                .id(1)
                .movieId(2)
                .userId(3)
                .text("Test")
                .build();

        Review review = reviewMapper.toReview(reviewDto);

        assertEquals(0, review.getId());
        assertEquals(2, review.getMovie().getId());
        assertEquals(3, review.getUser().getId());
        assertEquals("Test", review.getDescription());
    }

    @Test
    @DisplayName("when Map Review then Return ReviewResponse")
    void whenMapReview_thenReturnReviewResponse() {
        Review review = Review.builder()
                .id(1)
                .movie(new Movie(1, "ABC", "ABC", Date.valueOf("2010-01-01"), "Text",
                        9.00, 90.00, "test path", 5, null, null))
                .user(new User(2, "John", "test@test.com", "12345", "ADMIN"))
                .description("COOL")
                .build();

        ReviewResponse reviewResponse = reviewMapper.toReviewResponse(review);

        assertEquals(1, reviewResponse.getId());
        assertEquals(2, reviewResponse.getUser().getId());
        assertEquals("John", reviewResponse.getUser().getNickName());
        assertEquals("test@test.com", reviewResponse.getUser().getEmail());
        assertEquals("12345", reviewResponse.getUser().getPassword());
        assertEquals("ADMIN", reviewResponse.getUser().getType());
        assertEquals("COOL", reviewResponse.getText());
    }
}