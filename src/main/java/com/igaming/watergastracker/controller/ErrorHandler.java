package com.igaming.watergastracker.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Exception handler for handling different types of exceptions.
 */
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(ErrorHandler.class);

    /**
     * Handles RuntimeException exception and returns a Bad Request response.
     *
     * @param ex      The exception.
     * @param request The web request.
     * @return A ResponseEntity with a Bad Request status and error message.
     */
    @ExceptionHandler({RuntimeException.class})
    public final ResponseEntity<String> handleRuntimeException(final Exception ex,
                                                               final WebRequest request) {
        LOGGER.log(Level.ERROR, ex);
        String errorMessage = "Error: " + ex.getMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    /**
     * Custom exception handler for handling MethodArgumentNotValidException.
     *
     * @param ex      The MethodArgumentNotValidException instance.
     * @param headers The HTTP headers for the response.
     * @param status  The HTTP status for the response.
     * @param request The web request.
     * @return A ResponseEntity containing the error response.
     */
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.add(error.getField() + ": " + error.getDefaultMessage()));

        ErrorResponse errorResponse = new ErrorResponse("Validation failed", errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Custom exception handler for handling HttpMessageNotReadableException.
     *
     * @param ex      The HttpMessageNotReadableException instance.
     * @param headers The HTTP headers for the response.
     * @param status  The HTTP status for the response.
     * @param request The web request.
     * @return A ResponseEntity containing the error response.
     */
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                               HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse("Malformed JSON request", Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    static class ErrorResponse {
        private final String message;
        private final List<String> errors;

        public ErrorResponse(String message, List<String> errors) {
            this.message = message;
            this.errors = errors;
        }

        public String getMessage() {
            return message;
        }

        public List<String> getErrors() {
            return errors;
        }
    }
}
