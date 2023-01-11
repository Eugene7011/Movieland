package com.podzirei.movieland.mapper;

import com.podzirei.movieland.dto.ReviewDto;
import com.podzirei.movieland.response.ReviewResponse;
import com.podzirei.movieland.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MovieMapper.class, UserMapper.class})
public interface ReviewMapper {

    @Mapping(target = "text", source = "description")
    ReviewResponse toReviewResponse(Review review);

    List<ReviewResponse> toReviewResponses(List<Review> reviews);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "movie.id", source = "movieId")
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "description", source = "text")
    Review toReview(ReviewDto reviewDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "movieId", source = "movie.id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "text", source = "description")
    ReviewDto toReviewDto(Review review);
}
