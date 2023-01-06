package com.podzirei.movieland.web.controller;

import com.podzirei.movieland.dto.CountryDto;
import com.podzirei.movieland.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/country", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    List<CountryDto> findAll() {
        return countryService.findAll();
    }
}
