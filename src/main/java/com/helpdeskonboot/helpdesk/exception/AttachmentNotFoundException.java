package com.helpdeskonboot.helpdesk.exception;

public class AttachmentNotFoundException extends RuntimeException {

    public AttachmentNotFoundException() {
        super("attachment not found");
    }
}
