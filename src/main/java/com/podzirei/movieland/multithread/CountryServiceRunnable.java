package com.podzirei.movieland.multithread;

import com.podzirei.movieland.entity.Country;
import com.podzirei.movieland.entity.Movie;
import com.podzirei.movieland.repository.JpaCountryRepository;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CountryServiceRunnable implements Runnable{

    private static final long WAITING_TASK_TIME = 5000;
    private JpaCountryRepository jpaCountryRepository;
    private List<Country> countries = new ArrayList<>();

    private Movie movie;

    public CountryServiceRunnable(JpaCountryRepository jpaCountryRepository, Movie movie) {
        this.jpaCountryRepository = jpaCountryRepository;
        this.movie = movie;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        long end = start + WAITING_TASK_TIME;
//        while (System.currentTimeMillis() < end){
            countries = jpaCountryRepository.findCountriesByMoviesContains(movie);
//            return;
//        }
//        Thread.currentThread().interrupt();
    }
}
