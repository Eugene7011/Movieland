package com.podzirei.movieland.service.impl;

import com.podzirei.movieland.dto.CountryDto;
import com.podzirei.movieland.mapper.CountryMapper;
import com.podzirei.movieland.repository.JpaCountryRepository;
import com.podzirei.movieland.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultCountryService implements CountryService {

    private final JpaCountryRepository jpaCountryRepository;
    private final CountryMapper countryMapper;

    @Override
    public List<CountryDto> findAll() {
        return countryMapper.toCountryDtos(jpaCountryRepository.findAll());
    }
}
