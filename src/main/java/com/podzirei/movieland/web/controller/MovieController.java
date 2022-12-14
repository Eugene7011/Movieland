package com.podzirei.movieland.web.controller;

import com.podzirei.movieland.dto.MovieDto;
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
    public List<MovieDto> getAll() {
        return movieService.findAll();
    }

    @GetMapping("/rating/{sortType}")
    public List<MovieDto> getAllByRatingSorted(@PathVariable("sortType") String sortType) {
        return movieService.findAllByRatingSorted(sortType);
    }

    @GetMapping("/price/{sortType}")
    public List<MovieDto> getAllByPriceSorted(@PathVariable("sortType") String sortType) {
        return movieService.findAllByPriceSorted(sortType);
    }

    @GetMapping("/random")
    public List<MovieDto> getRandom() {
        return movieService.findThreeRandom();
    }

}
