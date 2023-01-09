package com.podzirei.movieland.mapper;

import com.podzirei.movieland.dto.CountryDto;
import com.podzirei.movieland.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring", uses = MovieMapper.class)
public interface CountryMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CountryDto toCountryDto(Country country);

    Set<CountryDto> toCountryDtos(Set<Country> contries);
}
