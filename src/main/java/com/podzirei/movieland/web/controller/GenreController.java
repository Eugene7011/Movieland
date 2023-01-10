package com.podzirei.movieland.web.controller;

import com.podzirei.movieland.dto.GenreDto;
import com.podzirei.movieland.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/genre", produces = MediaType.APPLICATION_JSON_VALUE)
public class GenreController {

    private static final Logger logger = LoggerFactory.getLogger(GenreController.class);
    private final GenreService genreService;

    @GetMapping
    public Set<GenreDto> findAll() {
        logger.info("Query get feedback by userId: ");
        return genreService.findAll();
    }
}
