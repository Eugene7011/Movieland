package com.podzirei.movieland.exception;

public class MovieNotFoundException extends RuntimeException {
    private static final String NO_MOVIE_BY_ID_MESSAGE = "There is no movie with id: %s";
    public MovieNotFoundException(String message) {
        super(message);
    }

    public MovieNotFoundException(int id) {
        super(String.format(NO_MOVIE_BY_ID_MESSAGE, id));
    }
}
