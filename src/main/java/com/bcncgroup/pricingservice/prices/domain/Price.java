package com.bcncgroup.pricingservice.prices.domain;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Domain record that represents a price applicable to a product for a given
 * brand
 * during a specific time window.
 *
 * <p>
 * Fields:
 * <ul>
 * <li>brandId - identifier of the brand
 * <li>startDate / endDate - inclusive time window where this price is valid
 * <li>priceList - identifier of the price list
 * <li>productId - identifier of the product
 * <li>priority - precedence when multiple prices apply
 * <li>price - monetary amount
 * <li>currency - ISO currency code
 * </ul>
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
