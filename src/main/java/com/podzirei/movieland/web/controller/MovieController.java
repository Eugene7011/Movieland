package com.podzirei.movieland.web.controller;

import com.podzirei.movieland.dto.MovieDto;
import com.podzirei.movieland.entity.SortDirection;
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

    @GetMapping("/genre/{genreId}")
    public List<MovieDto> getByGenreId(@PathVariable("genreId") int genreId) {
        return movieService.findByGenreId(genreId);
    }

    @GetMapping("/genre/{genreId}/?rating={sortType}")
    public List<MovieDto> getByGenreIdOrderByRating(@PathVariable("genreId") int genreId,
                                                    @PathVariable("sortType") SortDirection sortType) {
        return movieService.findByGenreIdOrderByRating(genreId, sortType);
    }

    //implement !!
    class MovieRequest{
        SortDirection rating;
        SortDirection price;
        Integer genreId;

        String searchedText;
        int count;
        int pageNumber;
    }

    @GetMapping("/genre/{genreId}/price/{sortType}")
    public List<MovieDto> getByGenreIdOrderByPrice(@PathVariable("genreId") int genreId,
                                                   @PathVariable("sortType") SortDirection sortType) {
        return movieService.findByGenreIdOrderByPrice(genreId, sortType);
    }

    @GetMapping("/rating/{sortType}")
    public List<MovieDto> getAllByRatingSorted(@PathVariable("sortType") SortDirection sortType) {
        return movieService.findAllByRatingSorted(sortType);
    }

    @GetMapping("/price/{sortType}")
    public List<MovieDto> getAllByPriceSorted(@PathVariable("sortType") SortDirection sortType) {
        return movieService.findAllByPriceSorted(sortType);
    }

    @GetMapping("/random")
    public List<MovieDto> getRandom() {
        return movieService.findRandom();
    }

}
