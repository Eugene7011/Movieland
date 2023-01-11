package com.podzirei.movieland.web.controller;

import com.podzirei.movieland.dto.MovieDto;
import com.podzirei.movieland.response.MovieResultResponse;
import com.podzirei.movieland.request.MovieRequest;
import com.podzirei.movieland.request.MovieUpdateRequest;
import com.podzirei.movieland.response.MovieResponse;
import com.podzirei.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/movie", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    private final MovieService movieService;

    @GetMapping
    public List<MovieResponse> findAll(MovieRequest movieRequest) {
        logger.info("Query get feedback by userId: ");
        return movieService.findAll(movieRequest);
    }

    @GetMapping("/{movieId}{currency}")
    public MovieResultResponse findByMovieId(@PathVariable("movieId") int movieId,
                                             String currency) {
        logger.info("Query get feedback by userId: ");
        if ("".equals(currency) || currency == null) {
            return movieService.findByMovieId(movieId);
        }
        return movieService.findByMovieId(movieId, currency);
    }

    @GetMapping("/random")
    public List<MovieResponse> getRandom() {
        logger.info("Query get feedback by userId: ");
        return movieService.findRandom();
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN', 'U')") //TODO: change only to ADMIN
    public MovieResultResponse addMovie(@RequestBody MovieDto movieDto) {
        logger.info("Query get feedback by userId: ");
        return movieService.add(movieDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'U')") //TODO: change only to ADMIN
    public MovieResultResponse update(@PathVariable("id") int movieId,
                                      @RequestBody MovieUpdateRequest movieUpdateRequest) {
        logger.info("Query get feedback by userId: ");
        return movieService.update(movieId, movieUpdateRequest);
    }
}
