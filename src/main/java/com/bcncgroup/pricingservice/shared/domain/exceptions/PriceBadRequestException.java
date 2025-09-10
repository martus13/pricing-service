package com.bcncgroup.pricingservice.shared.domain.exceptions;

import java.io.Serial;

public class PriceBadRequestException extends RuntimeException {
    @Serial private static final long serialVersionUID = 8997154055808845339L;

    public PriceBadRequestException(String message, Throwable e) {
        super(message, e);
    }
}
