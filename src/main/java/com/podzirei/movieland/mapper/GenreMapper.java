package com.podzirei.movieland.mapper;

import com.podzirei.movieland.dto.GenreDto;
import com.podzirei.movieland.entity.Genre;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    Set<GenreDto> genresToGenresDtos(Set<Genre> genres);

    GenreDto genreToGenreDto(Genre genre);

    Genre genreDtoToGenre(GenreDto genreDto);
}
