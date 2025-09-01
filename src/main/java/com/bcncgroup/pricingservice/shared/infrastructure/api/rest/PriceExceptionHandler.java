package com.bcncgroup.pricingservice.shared.infrastructure.api.rest;

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

    @ExceptionHandler(value = PriceNotFoundException.class)
    public ResponseEntity<ProblemDetails> handle(PriceNotFoundException e) {
        log.debug("Price not found", e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ProblemDetails(
                        String.valueOf(HttpStatus.NOT_FOUND.value()),
                        "Price Not Found",
                        e.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ProblemDetails> handle(MethodArgumentTypeMismatchException e) {
        log.debug(e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ProblemDetails(
                        String.valueOf(HttpStatus.BAD_REQUEST.value()),
                        "Petición no válida",
                        "La petición enviada no tiene el formato adecuado"));
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<ProblemDetails> handle(MissingServletRequestParameterException e) {
        log.debug(e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ProblemDetails(
                        String.valueOf(HttpStatus.BAD_REQUEST.value()),
                        "Petición no válida",
                        "Faltan parámetros en la petición"));
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ProblemDetails> handle(HttpRequestMethodNotSupportedException e) {
        log.warn("Method Not Allowed", e);
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ProblemDetails(
                        String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()),
                        "Method Not Allowed",
                        String.format("Request method '%s' not supported", e.getMethod())));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ProblemDetails> handle(Exception e) {
        log.error("Unhandled exception", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ProblemDetails(
                        String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                        "Internal Server Error",
                        e.getMessage() != null ? e.getMessage() : "Unexpected error"));
    }
}
