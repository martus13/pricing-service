package com.bcncgroup.pricingservice.prices.infrastructure.api.rest.models;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for price information returned by the API.
 *
 * @param brandId Brand identifier.
 * @param startDate Start of validity period (inclusive).
 * @param endDate End of validity period (inclusive).
 * @param priceList Price tariff identifier.
 * @param productId Product identifier.
 * @param price Final retail price.
 * @param currency Currency code.
 */
public record PriceResponse(
        Long brandId,
        Instant startDate,
        Instant endDate,
        Long priceList,
        Long productId,
        BigDecimal price,
        String currency) {}
