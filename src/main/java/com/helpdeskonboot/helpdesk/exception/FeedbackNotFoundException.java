package com.helpdeskonboot.helpdesk.exception;

public class FeedbackNotFoundException extends RuntimeException {

    public FeedbackNotFoundException() {
        super("feedback not found");
    }
}
