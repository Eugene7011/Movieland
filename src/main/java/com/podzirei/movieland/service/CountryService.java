package com.podzirei.movieland.service;

import com.podzirei.movieland.dto.CountryDto;

import java.util.List;

public interface CountryService {

    List<CountryDto> findAll();
}
