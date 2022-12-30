package com.podzirei.movieland.web.controller;

import com.podzirei.movieland.dto.GenreDto;
import com.podzirei.movieland.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/genre", produces = MediaType.APPLICATION_JSON_VALUE)
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public List<GenreDto> findAll() {
        return genreService.findAll();
    }

}
