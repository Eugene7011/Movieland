package com.podzirei.movieland.exception;

public class ReviewNotFoundException extends RuntimeException {
    private static final String NO_REVIEW_BY_MOVIE_ID_MESSAGE = "There is no review with movie id: %s";
    public ReviewNotFoundException(String message) {
        super(message);
    }

    public ReviewNotFoundException(int id) {
        super(String.format(NO_REVIEW_BY_MOVIE_ID_MESSAGE, id));
    }

}
