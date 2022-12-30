package com.podzirei.movieland.web.controller.movie;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieRequest {

    private SortDirection rating;
    private SortDirection price;
    private Integer genre;
}
