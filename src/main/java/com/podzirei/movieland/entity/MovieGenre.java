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
@Table(name = "movie_genre")
public class MovieGenre {

    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;
}
