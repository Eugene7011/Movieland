package com.podzirei.movieland.service;

import com.podzirei.movieland.dto.ReviewDto;
import com.podzirei.movieland.web.controller.review.ReviewPostRequest;

public interface ReviewService {

    ReviewDto addReview(String userEmail, ReviewPostRequest reviewPostRequest);
}
