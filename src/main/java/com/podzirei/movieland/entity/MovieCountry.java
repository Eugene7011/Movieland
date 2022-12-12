package com.podzirei.movieland.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movie_country")
public class MovieCountry {

    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;
}
