package com.podzirei.movieland;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MovielandApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovielandApplication.class, args);
    }

}
