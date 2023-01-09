package com.podzirei.movieland.service;

import com.podzirei.movieland.dto.CountryDto;

import java.util.Set;

public interface CountryService {

    Set<CountryDto> findAll();
}
