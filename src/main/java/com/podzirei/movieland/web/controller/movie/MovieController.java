package com.podzirei.movieland.web.controller.movie;

import com.podzirei.movieland.dto.MovieDto;
import com.podzirei.movieland.dto.MovieResultDto;
import com.podzirei.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/movie", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<MovieDto> findAll(MovieRequest movieRequest) {
        return movieService.findAll(movieRequest);
    }

    @GetMapping("/{movieId}")
    public MovieResultDto findByMovieId(@PathVariable("movieId") int movieId) {
        return movieService.findByMovieId(movieId);
    }

    @GetMapping("/random")
    public List<MovieDto> getRandom() {
        return movieService.findRandom();
    }

}
