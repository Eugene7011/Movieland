package com.podzirei.movieland.mapper;

import com.podzirei.movieland.dto.MovieDto;
import com.podzirei.movieland.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    List<MovieDto> moviesToMovieDtos(List<Movie> movies);

    @Mapping(target = "yearOfRelease", source = "yearOfRelease", qualifiedByName = "mapDateFromMovie")
    MovieDto movieToMovieDto(Movie movie);

    @Mapping(target = "description", ignore = true)
    @Mapping(target = "votes", ignore = true)
    @Mapping(target = "yearOfRelease", source = "yearOfRelease", qualifiedByName = "mapDateFromMovieDto")
    Movie movieDtoToMovie(MovieDto movieDto);

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
