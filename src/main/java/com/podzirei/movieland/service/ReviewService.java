package com.podzirei.movieland.service;

import com.podzirei.movieland.dto.ReviewDto;
import com.podzirei.movieland.request.ReviewPostRequest;

public interface ReviewService {

    ReviewDto addReview(String userEmail, ReviewPostRequest reviewPostRequest);
}
