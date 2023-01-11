package com.podzirei.movieland.response;


import com.podzirei.movieland.dto.CountryDto;
import com.podzirei.movieland.dto.GenreDto;
import com.podzirei.movieland.response.ReviewResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieResultResponse {

    private int id;
    private String nameRussian;
    private String nameNative;
    private String yearOfRelease;
    private String description;
    private Double rating;
    private Double price;
    private String picturePath;
    private Set<CountryDto> countries;
    private Set<GenreDto> genres;
    private List<ReviewResponse> reviews;
}
