package com.bcncgroup.pricingservice.prices.infrastructure.api.rest.models;

import java.math.BigDecimal;
import java.time.Instant;

public record PriceResponse(
        Long brandId,
        Instant startDate,
        Instant endDate,
        Long priceList,
        Long productId,
        BigDecimal price,
        String currency) {}
