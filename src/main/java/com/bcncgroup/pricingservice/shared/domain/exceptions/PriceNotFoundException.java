package com.bcncgroup.pricingservice.shared.domain.exceptions;

import java.io.Serial;

/**
 * Exception for not found prices (HTTP 404).
 */
public class PriceNotFoundException extends RuntimeException {
    
    @Serial
    private static final long serialVersionUID = 6055020030729144232L;

    /**
     * Constructor.
     *
     * @param message Error message.
     */
    public PriceNotFoundException(String message) {
        super(message);
    }
}
