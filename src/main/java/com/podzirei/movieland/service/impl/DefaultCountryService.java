package com.podzirei.movieland.service.impl;

import com.podzirei.movieland.dto.CountryDto;
import com.podzirei.movieland.mapper.CountryMapper;
import com.podzirei.movieland.repository.JpaCountryRepository;
import com.podzirei.movieland.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultCountryService implements CountryService {

    private final JpaCountryRepository jpaCountryRepository;
    private final CountryMapper countryMapper;

    @Override
    public Set<CountryDto> findAll() {
        return countryMapper.toCountryDtos(new HashSet<>(jpaCountryRepository.findAll()));
    }
}
