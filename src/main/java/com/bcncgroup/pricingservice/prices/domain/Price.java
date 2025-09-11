package com.bcncgroup.pricingservice.prices.domain;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Price for a product in a brand's catalog.
 *
 * @param brandId Brand identifier.
 * @param startDate Start date (inclusive).
 * @param endDate End date (inclusive).
 * @param priceList Price list or tariff identifier.
 * @param productId Product identifier.
 * @param priority Priority for disambiguation.
 * @param price Final retail price.
 * @param currency Currency code.
 */
public record Price(
                Long brandId,
                Instant startDate,
                Instant endDate,
                Long priceList,
                Long productId,
                Integer priority,
                BigDecimal price,
                String currency) {
}
