package com.podzirei.movieland.web.controller.review;

import com.podzirei.movieland.dto.ReviewDto;
import com.podzirei.movieland.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/review", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'U')")
    public ReviewDto addReview(Authentication authentication,
                               ReviewPostRequest reviewPostRequest) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return reviewService.addReview(userDetails.getUsername(), reviewPostRequest);
    }
}
