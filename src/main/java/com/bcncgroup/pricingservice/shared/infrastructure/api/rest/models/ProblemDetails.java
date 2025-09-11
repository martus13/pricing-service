package com.bcncgroup.pricingservice.shared.infrastructure.api.rest.models;

/**
 * DTO for standardized API error responses.
 *
 * @param status HTTP status code.
 * @param title Short summary of the problem.
 * @param detail Explanation of the problem.
 */
public record ProblemDetails(String status, String title, String detail) {
}
