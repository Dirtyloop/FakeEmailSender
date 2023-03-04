package com.komfortcieplny.FakeEmailSender.user.exceptions;

public class UserNotFoundException  extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
