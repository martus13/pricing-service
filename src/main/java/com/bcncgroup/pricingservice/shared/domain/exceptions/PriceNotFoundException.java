package com.bcncgroup.pricingservice.shared.domain.exceptions;

import java.io.Serial;

public class PriceNotFoundException extends RuntimeException {
    @Serial private static final long serialVersionUID = 6055020030729144232L;

    public PriceNotFoundException(String message) {
        super(message);
    }
}
