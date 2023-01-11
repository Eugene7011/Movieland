package com.podzirei.movieland.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieRequest {

    private SortDirection rating;
    private SortDirection price;
    private Integer genre;
}
