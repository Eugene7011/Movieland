package com.podzirei.movieland.exception;

public class CurrencyNotFoundException extends RuntimeException {
    private static final String NO_CURRENCY_MESSAGE = "There is no user with email: %s";

    public CurrencyNotFoundException(String message) {
        super(String.format(NO_CURRENCY_MESSAGE, message));
    }
}
