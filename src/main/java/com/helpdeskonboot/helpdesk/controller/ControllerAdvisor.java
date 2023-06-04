package com.helpdeskonboot.helpdesk.controller;


import com.helpdeskonboot.helpdesk.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    @Autowired
    private final static String TIMESTAMP = "timestamp";
    private final static String MESSAGE = "message";
    private static final Logger logger = LoggerFactory.getLogger(ControllerAdvisor.class);


    @ExceptionHandler(value = {UserNotFoundException.class,
            TicketNotFoundException.class,
            ActionNotFoundException.class,
            AttachmentNotFoundException.class,
            CategoryNotFoundException.class,
            FeedbackNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Throwable e) {
        return prepareResponseForSubject(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongTicketChoiseEcxeption.class)
    public ResponseEntity<Object> handleNotAcceptableException (Throwable e) {
        return prepareResponseForSubject(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    private ResponseEntity<Object> prepareResponseForSubject(String responseSubject, HttpStatus responseStatus) {
        logger.error(LocalDateTime.now() + ": " + responseSubject);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, responseSubject);
        return new ResponseEntity<>(body, responseStatus);
    }

}
