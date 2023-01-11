package com.podzirei.movieland.mapper;

import com.podzirei.movieland.dto.MovieDto;
import com.podzirei.movieland.entity.Country;
import com.podzirei.movieland.entity.Genre;
import com.podzirei.movieland.entity.Movie;
import com.podzirei.movieland.response.MovieResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {MovieMapperImpl.class})
class MovieMapperTest {

    @Autowired
    private MovieMapper movieMapper;

    @Test
    @DisplayName("when Map MovieDto then Return Movie")
    void whenMapMovieDto_thenReturnMovie() {
        MovieDto movieDto = MovieDto.builder()
                .nameRussian("Терминатор")
                .nameNative("Terminator")
                .yearOfRelease("2000")
                .description("Old school movie")
                .price(99.00)
                .picturePath("http/google.com/path")
                .countries(Set.of(1, 2))
                .genres(Set.of(2, 3))
                .build();

        Movie movie = movieMapper.toMovie(movieDto);

        assertEquals(0, movie.getId());
        assertEquals("Терминатор", movie.getNameRussian());
        assertEquals("Terminator", movie.getNameNative());
        assertEquals(Date.valueOf("2000-01-01"), movie.getYearOfRelease());
        assertEquals("Old school movie", movie.getDescription());
        assertEquals(99.00, movie.getPrice());
        assertEquals("http/google.com/path", movie.getPicturePath());
        assertEquals(0, movie.getVotes());
        assertTrue(movie.getGenres().isEmpty());
        assertTrue(movie.getCountries().isEmpty());
    }

    @Test
    @DisplayName("when Map Movie then Return MovieResponse")
    void whenMapMovie_thenReturnMovieResponse() {
        Movie movie = Movie.builder()
                .id(1)
                .nameRussian("Терминатор")
                .nameNative("Terminator")
                .yearOfRelease(Date.valueOf("2000-01-01"))
                .description("Old school movie")
                .rating(10.00)
                .price(99.00)
                .picturePath("http/google.com/path")
                .countries(Set.of(new Country(1, "Ukraine")))
                .genres(Set.of(new Genre(2, "Ужас")))
                .build();

        MovieResponse movieResponse = movieMapper.toMovieResponse(movie);

        assertEquals(1, movieResponse.getId());
        assertEquals("Терминатор", movieResponse.getNameRussian());
        assertEquals("Terminator", movieResponse.getNameNative());
        assertEquals("2000", movieResponse.getYearOfRelease());
        assertEquals(10.00, movieResponse.getRating());
        assertEquals(99.00, movieResponse.getPrice());
        assertEquals("http/google.com/path", movieResponse.getPicturePath());
    }
}