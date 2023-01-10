package com.podzirei.movieland.service;

import com.podzirei.movieland.entity.Country;
import com.podzirei.movieland.entity.Genre;
import com.podzirei.movieland.entity.Movie;
import com.podzirei.movieland.repository.MovieRepository;
import com.podzirei.movieland.web.controller.movie.MovieRequest;
import com.podzirei.movieland.web.controller.movie.MovieResponse;
import com.podzirei.movieland.web.controller.movie.SortDirection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class MovieServiceTest {

    @Autowired
    private MovieService movieService;
    private MovieResponse movieResponseFirst;
    private MovieResponse movieResponseSecond;
    private Movie movieFirst;
    private Movie movieSecond;

    @MockBean
    private MovieRepository movieRepository;

    @BeforeEach
    public void setup() {
        movieFirst = Movie.builder()
                .id(2)
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

        movieSecond = Movie.builder()
                .id(1)
                .nameRussian("Побег из Шоушенка")
                .nameNative("The Shawshank Redemption")
                .yearOfRelease(Date.valueOf("1994-01-01"))
                .description("Great movie")
                .rating(8.89)
                .price(123.45)
                .picturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg")
                .countries(Set.of(new Country(2, "England")))
                .genres(Set.of(new Genre(3, "Комедия")))
                .build();

        movieResponseFirst = MovieResponse.builder()
                .id(2)
                .nameRussian("Терминатор")
                .nameNative("Terminator")
                .yearOfRelease("2000")
                .rating(10.00)
                .price(99.00)
                .picturePath("http/google.com/path")
                .build();

        movieResponseSecond = MovieResponse.builder()
                .id(1)
                .nameRussian("Побег из Шоушенка")
                .nameNative("The Shawshank Redemption")
                .yearOfRelease("1994")
                .rating(8.89)
                .price(123.45)
                .picturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg")
                .build();

        when(movieRepository.findAll()).thenReturn(List.of(movieFirst, movieSecond));
    }

    @Test
    @DisplayName("when Get All Movies then Correct Result Returned")
    void whenGetAllMovies_thenCorrectResultReturned() {
        List<MovieResponse> movieResponses = movieService.findAll();

        assertEquals(movieResponses.get(0).getId(), movieResponseFirst.getId());
        assertEquals(movieResponses.get(0).getNameRussian(), movieResponseFirst.getNameRussian());
        assertEquals(movieResponses.get(0).getNameNative(), movieResponseFirst.getNameNative());
        assertEquals(movieResponses.get(0).getPrice(), movieResponseFirst.getPrice());
        assertEquals(movieResponses.get(0).getRating(), movieResponseFirst.getRating());
        assertEquals(movieResponses.get(0).getYearOfRelease(), movieResponseFirst.getYearOfRelease());

        assertEquals(movieResponses.get(1).getId(), movieResponseSecond.getId());
        assertEquals(movieResponses.get(1).getNameRussian(), movieResponseSecond.getNameRussian());
        assertEquals(movieResponses.get(1).getNameNative(), movieResponseSecond.getNameNative());
        assertEquals(movieResponses.get(1).getPrice(), movieResponseSecond.getPrice());
        assertEquals(movieResponses.get(1).getRating(), movieResponseSecond.getRating());
        assertEquals(movieResponses.get(1).getYearOfRelease(), movieResponseSecond.getYearOfRelease());
    }

    @Test
    @DisplayName("when Get All Movies By Rating Asc then Correct Result Returned")
    void whenGetAllMoviesByRatingAsc_thenCorrectResultReturned() {
        MovieRequest movieRequest = new MovieRequest();
        movieRequest.setRating(SortDirection.ASC);

        when(movieRepository.findAll(movieRequest)).thenReturn(List.of(movieSecond, movieFirst));
        List<MovieResponse> movieResponses = movieService.findAll(movieRequest);
        assertEquals(8.89, movieResponses.get(0).getRating());
        assertEquals(10.00, movieResponses.get(1).getRating());
    }

    @Test
    @DisplayName("when Get All Movies By Rating Desc then Correct Result Returned")
    void whenGetAllMoviesByRatingDesc_thenCorrectResultReturned() {
        MovieRequest movieRequest = new MovieRequest();
        movieRequest.setRating(SortDirection.DESC);

        when(movieRepository.findAll(movieRequest)).thenReturn(List.of(movieFirst, movieSecond));
        List<MovieResponse> movieResponses = movieService.findAll(movieRequest);
        assertEquals(99.00, movieResponses.get(0).getPrice());
        assertEquals(123.45, movieResponses.get(1).getPrice());
    }

    @Test
    @DisplayName("when Get All Movies By Price Asc then Correct Result Returned")
    void whenGetAllMoviesByPrice_thenCorrectResultReturned() {
        MovieRequest movieRequest = new MovieRequest();
        movieRequest.setPrice(SortDirection.ASC);

        when(movieRepository.findAll(movieRequest)).thenReturn(List.of(movieSecond, movieFirst));
        List<MovieResponse> movieResponses = movieService.findAll(movieRequest);
        assertEquals(8.89, movieResponses.get(0).getRating());
        assertEquals(10.00, movieResponses.get(1).getRating());
    }

}