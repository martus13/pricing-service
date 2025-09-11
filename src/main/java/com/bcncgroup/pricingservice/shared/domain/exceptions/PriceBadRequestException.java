package com.bcncgroup.pricingservice.shared.domain.exceptions;

import java.io.Serial;

/**
 * Exception for invalid or malformed price requests (HTTP 400).
 */
public class PriceBadRequestException extends RuntimeException {
    
    @Serial
    private static final long serialVersionUID = 8997154055808845339L;

    /**
     * Constructor.
     *
     * @param message Error message.
     * @param e Root cause.
     */
    public PriceBadRequestException(String message, Throwable e) {
        super(message, e);
    }
}
