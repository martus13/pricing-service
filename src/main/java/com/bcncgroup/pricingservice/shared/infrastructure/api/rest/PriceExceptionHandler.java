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

/**
 * Global exception handler for the pricing service's REST controllers.
 * <p>
 * This class uses {@link ControllerAdvice} to centralize exception handling
 * and convert them into standardized HTTP responses with a
 * {@link ProblemDetails} body,
 * thus providing consistent error communication to API clients.
 * </p>
 */
@JBossLog
@ControllerAdvice
public class PriceExceptionHandler {

  /**
   * Handles {@link PriceNotFoundException} which occurs when a price cannot be
   * found for the specified criteria.
   * <p>
   * Returns an HTTP 404 (Not Found) response.
   * </p>
   *
   * @param e the thrown {@link PriceNotFoundException}.
   * @return a {@link ResponseEntity} with status 404 and a {@link ProblemDetails}
   *         body.
   */
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

  /**
   * Handles {@link PriceBadRequestException} which indicates an invalid request
   * from the client due to business logic.
   * <p>
   * Returns an HTTP 400 (Bad Request) response.
   * </p>
   *
   * @param e the thrown {@link PriceBadRequestException}.
   * @return a {@link ResponseEntity} with status 400 and a {@link ProblemDetails}
   *         body.
   */
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

  /**
   * Handles {@link MethodArgumentTypeMismatchException} which arises when a
   * request parameter (e.g., in a path variable or query param) has an incorrect
   * type
   * (e.g., a number was expected but a string was received).
   * <p>
   * Returns an HTTP 400 (Bad Request) response.
   * </p>
   *
   * @param e the thrown {@link MethodArgumentTypeMismatchException}.
   * @return a {@link ResponseEntity} with status 400 and a generic message.
   */
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

  /**
   * Handles {@link MissingServletRequestParameterException} which occurs when
   * a required request parameter is missing.
   * <p>
   * Returns an HTTP 400 (Bad Request) response.
   * </p>
   *
   * @param e the thrown {@link MissingServletRequestParameterException}.
   * @return a {@link ResponseEntity} with status 400 and the error details.
   */
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

  /**
   * Handles {@link HttpRequestMethodNotSupportedException} which occurs when an
   * endpoint is called with an unsupported HTTP method (e.g., using GET on an
   * endpoint that only supports POST).
   * <p>
   * Returns an HTTP 405 (Method Not Allowed) response.
   * </p>
   *
   * @param e the thrown {@link HttpRequestMethodNotSupportedException}.
   * @return a {@link ResponseEntity} with status 405 detailing the unsupported
   *         method.
   */
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

  /**
   * Handles {@link IllegalArgumentException} as a generic way to catch invalid
   * arguments that have not been caught by more specific validations.
   * <p>
   * Returns an HTTP 400 (Bad Request) response.
   * </p>
   *
   * @param e the thrown {@link IllegalArgumentException}.
   * @return a {@link ResponseEntity} with status 400 and a generic message.
   */
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

  /**
   * Fallback handler to catch any exception not explicitly handled by other
   * handlers.
   * <p>
   * This prevents leaking stack traces to the client and ensures that a response
   * in the {@link ProblemDetails} format is always returned.
   * It returns an HTTP 500 (Internal Server Error) response.
   * </p>
   *
   * @param e the unexpected exception thrown.
   * @return a {@link ResponseEntity} with status 500 and a generic error message.
   */
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
