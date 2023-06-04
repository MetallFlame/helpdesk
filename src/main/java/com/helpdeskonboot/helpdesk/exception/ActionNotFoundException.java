package com.helpdeskonboot.helpdesk.exception;

public class ActionNotFoundException extends RuntimeException {

    public ActionNotFoundException() {
        super("action not found");
    }
}
