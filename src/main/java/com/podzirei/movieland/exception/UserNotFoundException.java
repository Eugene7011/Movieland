package com.podzirei.movieland.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private static final String NO_USER_BY_EMAIL_MESSAGE = "There is no user with email: %s";

    public UserNotFoundException(String email) {
        super(String.format(NO_USER_BY_EMAIL_MESSAGE, email));
    }

}
