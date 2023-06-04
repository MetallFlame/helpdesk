package com.helpdeskonboot.helpdesk.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("user not found");
    }
}
