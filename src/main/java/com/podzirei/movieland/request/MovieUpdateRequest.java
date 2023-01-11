package com.podzirei.movieland.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class MovieUpdateRequest {
    private String nameRussian;
    private String nameNative;
    private String picturePath;
    private Set<Integer> countries;
    private Set<Integer> genres;
}
