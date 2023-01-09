package com.podzirei.movieland.mapper;

import com.podzirei.movieland.dto.MovieDto;
import com.podzirei.movieland.entity.Movie;
import com.podzirei.movieland.web.controller.movie.MovieResponse;
import com.podzirei.movieland.web.controller.movie.MovieUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Mapper(componentModel = "spring", uses = {GenreMapper.class, ReviewMapper.class})
public interface MovieMapper {

    List<MovieResponse> toMovieResponses(List<Movie> movies);

    @Mapping(target = "yearOfRelease", source = "yearOfRelease", qualifiedByName = "mapDateFromMovie")
    MovieResponse toMovieResponse(Movie movie);

    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "countries", ignore = true)
    @Mapping(target = "yearOfRelease", source = "yearOfRelease", qualifiedByName = "mapDateFromMovie")
    MovieDto toMovieDto(Movie movie);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "countries", ignore = true)
    @Mapping(target = "yearOfRelease", source = "yearOfRelease", qualifiedByName = "mapDateFromMovieDto")
    Movie toMovie(MovieDto movieDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "yearOfRelease", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "votes", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "countries", ignore = true)
    Movie update(@MappingTarget Movie movie, MovieUpdateRequest movieUpdateRequest);

    @Named("mapDateFromMovie")
    default String mapDateFromMovie(Date date) {
        SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
        return formatYear.format(date);
    }

    @Named("mapDateFromMovieDto")
    default Date mapDateFromMovieDto(String realesedDate) {
        return Date.valueOf(realesedDate + "-01-01");
    }
}
