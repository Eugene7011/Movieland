package com.podzirei.movieland.web.controller;

import com.podzirei.movieland.dto.CountryDto;
import com.podzirei.movieland.service.CountryService;
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
@RequestMapping(path = "/api/v1/country", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {

    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);
    private final CountryService countryService;

    @GetMapping
    Set<CountryDto> findAll() {
        logger.info("Query get feedback by userId: ");
        return countryService.findAll();
    }
}
