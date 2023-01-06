package com.podzirei.movieland.web.controller.movie;

import com.podzirei.movieland.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponse {
    private int id;
    private UserDto user;
    private String text;
}
