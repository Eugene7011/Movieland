package com.podzirei.movieland.web.controller.movie;


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
public class MovieResponse {

    private int id;
    private String nameRussian;
    private String nameNative;
    private String yearOfRelease;
    private Double rating;
    private Double price;
    private String picturePath;
}
