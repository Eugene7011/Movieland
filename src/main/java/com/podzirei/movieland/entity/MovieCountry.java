package com.podzirei.movieland.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.Table;

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
