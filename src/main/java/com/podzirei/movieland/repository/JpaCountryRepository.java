package com.podzirei.movieland.repository;

import com.podzirei.movieland.entity.Country;
import com.podzirei.movieland.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaCountryRepository extends JpaRepository<Country, Integer> {
    List<Country> findCountriesByMoviesContains(Movie movie);
}