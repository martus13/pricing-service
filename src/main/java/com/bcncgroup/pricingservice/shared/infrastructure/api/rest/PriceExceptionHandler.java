package com.bcncgroup.pricingservice.shared.infrastructure.api.rest;

import com.bcncgroup.pricingservice.shared.domain.exceptions.PriceNotFoundException;
import com.bcncgroup.pricingservice.shared.infrastructure.api.rest.models.ProblemDetails;
import lombok.extern.jbosslog.JBossLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
