package com.podzirei.movieland.mapper;

import com.podzirei.movieland.dto.ReviewDto;
import com.podzirei.movieland.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MovieMapper.class, UserMapper.class})
public interface ReviewMapper {

    @Mapping(target = "text", source = "description")
    ReviewDto toReviewDto(Review review);

    List<ReviewDto> toReviewDtos(List<Review> reviews);
}
