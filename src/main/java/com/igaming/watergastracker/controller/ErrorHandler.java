package com.igaming.watergastracker.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception handler for handling different types of exceptions.
 */
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(ErrorHandler.class);

    /**
     * Handles runtime exceptions and returns a Bad Request response.
     *
     * @param ex      The runtime exception.
     * @param request The web request.
     * @return A ResponseEntity with a Bad Request status and error message.
     */
    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<String> handleRuntimeException(final RuntimeException ex,
                                                               final WebRequest request) {
        LOGGER.log(Level.ERROR, ex);
        String errorMessage = "Error: " + ex.getMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
