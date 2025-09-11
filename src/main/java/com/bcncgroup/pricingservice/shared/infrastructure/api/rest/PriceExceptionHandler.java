package com.bcncgroup.pricingservice.shared.infrastructure.api.rest;

import com.bcncgroup.pricingservice.shared.domain.exceptions.PriceBadRequestException;
import com.bcncgroup.pricingservice.shared.domain.exceptions.PriceNotFoundException;
import com.bcncgroup.pricingservice.shared.infrastructure.api.rest.models.ProblemDetails;
import lombok.extern.jbosslog.JBossLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@JBossLog
@ControllerAdvice
public class PriceExceptionHandler {

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ProblemDetails> handle(PriceNotFoundException e) {
        log.debug("Price not found", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        new ProblemDetails(
                                String.valueOf(HttpStatus.NOT_FOUND.value()),
                                "Price Not Found",
                                e.getMessage()));
    }

    @ExceptionHandler(PriceBadRequestException.class)
    public ResponseEntity<ProblemDetails> handle(PriceBadRequestException e) {
        log.debug("Bad request", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        new ProblemDetails(
                                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                                "Bad request",
                                e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ProblemDetails> handle(MethodArgumentTypeMismatchException e) {
        log.debug("Invalid request parameters", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        new ProblemDetails(
                                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                                "Bad Request",
                                "The request has an invalid format"));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ProblemDetails> handle(MissingServletRequestParameterException e) {
        log.debug("Missing required request parameters", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        new ProblemDetails(
                                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                                "Bad Request",
                                "Missing required request parameters"));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ProblemDetails> handle(HttpRequestMethodNotSupportedException e) {
        log.warn("Method Not Allowed", e);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(
                        new ProblemDetails(
                                String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()),
                                "Method Not Allowed",
                                String.format("Request method '%s' not supported",
                                        e.getMethod())));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemDetails> handle(IllegalArgumentException e) {
        log.debug("Invalid request parameters", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        new ProblemDetails(
                                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                                "Bad Request",
                                "Invalid request parameters"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetails> handle(Exception e) {
        log.error("Unhandled exception", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ProblemDetails(
                                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                                "Internal Server Error",
                                e.getMessage() != null ? e.getMessage() : "Unexpected error"));
    }
}
