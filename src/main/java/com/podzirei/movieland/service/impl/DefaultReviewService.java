package com.podzirei.movieland.service.impl;

import com.podzirei.movieland.dto.ReviewDto;
import com.podzirei.movieland.dto.UserDto;
import com.podzirei.movieland.entity.Review;
import com.podzirei.movieland.mapper.ReviewMapper;
import com.podzirei.movieland.repository.JpaReviewRepository;
import com.podzirei.movieland.service.ReviewService;
import com.podzirei.movieland.service.UserService;
import com.podzirei.movieland.request.ReviewPostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultReviewService implements ReviewService {

    private final UserService userService;
    private final JpaReviewRepository jpaReviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public ReviewDto addReview(String userEmail, ReviewPostRequest reviewPostRequest) {
        UserDto user = userService.findUserByEmail(userEmail);
        ReviewDto reviewDto = ReviewDto.builder()
                .userId(user.getId())
                .movieId(reviewPostRequest.getMovieId())
                .text(reviewPostRequest.getText())
                .build();
        Review review = reviewMapper.toReview(reviewDto);

        return reviewMapper.toReviewDto(jpaReviewRepository.save(review));
    }
}
