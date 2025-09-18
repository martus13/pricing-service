package com.bcncgroup.pricingservice.shared.infrastructure.api.rest;

import com.bcncgroup.pricingservice.shared.domain.exceptions.PriceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
* Global exception handler for the pricing service's REST controllers.
* <p>
* This class uses {@link RestControllerAdvice} to centralize exception handling
* and convert them into standardized HTTP responses with a
* {@link ProblemDetail} body,
* this providing consistent error communication to API clients.
* </p>
*/
@Slf4j
@RestControllerAdvice
public class PriceExceptionHandler {

    // 400 – Missing query param
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ProblemDetail handleMissingServletRequestParameter(MissingServletRequestParameterException e) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Required request parameter '%s' is missing".formatted(e.getParameterName()));
        pd.setTitle("Invalid parameters");
        return pd;
    }

    // 400 – Bad format (e.g., date not ISO-8601)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
        String param = e.getName();
        String value = String.valueOf(e.getValue());
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Parameter '%s' with value '%s' has an invalid format".formatted(param, value));
        pd.setTitle("Invalid parameters");
        return pd;
    }

    // 404 – No applicable price
    @ExceptionHandler(PriceNotFoundException.class)
    public ProblemDetail handlePriceNotFound(PriceNotFoundException e) {
        log.debug("Price not found", e);
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        pd.setTitle("Price Not Found");
        return pd;
    }

    // 405 - unsupported HTTP method (e.g., using GET on an endpoint that only supports POST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ProblemDetail handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        log.warn("Method Not Allowed", e);
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                HttpStatus.METHOD_NOT_ALLOWED,
                "Request method '%s' not supported".formatted(e.getMethod()));
        pd.setTitle("Method Not Allowed");
        return pd;
    }

    // 500 – Fallback
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception e) {
        log.error("Unhandled exception", e);
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage() != null ? e.getMessage() : "Unexpected error");
        pd.setTitle("Internal Server Error");
        return pd;
    }
}
