package com.podzirei.movieland.dto;


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
public class MovieDto {

    private int id;
    private String nameRussian;
    private String nameNative;
    private String yearOfRelease;
    private Double rating;
    private Double price;
    private String picturePath;
}
