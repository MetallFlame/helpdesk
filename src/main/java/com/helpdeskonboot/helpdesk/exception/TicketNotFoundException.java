package com.helpdeskonboot.helpdesk.exception;

public class TicketNotFoundException extends RuntimeException {

    public TicketNotFoundException() {
        super("ticket not found");
    }
}
