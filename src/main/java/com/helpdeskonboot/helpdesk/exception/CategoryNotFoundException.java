package com.helpdeskonboot.helpdesk.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException() {
        super("category not found");
    }
}
