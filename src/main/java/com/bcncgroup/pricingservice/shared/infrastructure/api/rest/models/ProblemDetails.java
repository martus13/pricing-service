package com.bcncgroup.pricingservice.shared.infrastructure.api.rest.models;

/**
 * Simple representation of error/problem details returned by REST APIs.
 *
 * @param status HTTP status or application error code
 * @param title  short title for the problem
 * @param detail human readable detail about the problem
 */
public record ProblemDetails(String status, String title, String detail) {
}
