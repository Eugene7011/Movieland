package com.podzirei.movieland.web.controller.movie;

import com.podzirei.movieland.dto.MovieDto;
import com.podzirei.movieland.dto.MovieResultDto;
import com.podzirei.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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

    private final MovieService movieService;

    @GetMapping
    public List<MovieResponse> findAll(MovieRequest movieRequest) {
        return movieService.findAll(movieRequest);
    }

    @GetMapping("/{movieId}{currency}")
    public MovieResultDto findByMovieId(@PathVariable("movieId") int movieId,
                                        String currency) {
        if ("".equals(currency) || currency == null) {
            return movieService.findByMovieId(movieId);
        }
        return movieService.findByMovieId(movieId, currency);
    }

    @GetMapping("/random")
    public List<MovieResponse> getRandom() {
        return movieService.findRandom();
    }

    @PostMapping("/add")
//    @PreAuthorize("hasAnyRole('ADMIN', 'U')") //TODO: change only to ADMIN
    public MovieResultDto addMovie(@RequestBody MovieDto movieDto) {
        return movieService.add(movieDto);
    }

    @PutMapping("/{id}")
    //    @PreAuthorize("hasAnyRole('ADMIN', 'U')") //TODO: change only to ADMIN
    public MovieResultDto update(@PathVariable("id") int movieId,
                                 @RequestBody MovieUpdateRequest movieUpdateRequest) {
        return movieService.update(movieId, movieUpdateRequest);
    }
}
