package com.bcncgroup.pricingservice.prices.application.port.out;

import com.bcncgroup.pricingservice.prices.domain.Price;
import java.time.Instant;
import java.util.Optional;

/**
 * Output port for accessing price data.
 */
public interface LoadPricePort {
    
    /**
     * Finds the applicable price for the given date, product, and brand.
     *
     * @param applicationDate Date and time to apply the price.
     * @param productId Product identifier.
     * @param brandId Brand identifier.
     * @return Optional with the applicable {@link Price}, or empty if not found.
     */
    Optional<Price> loadApplicablePrice(Instant applicationDate, Long productId, Long brandId);
}
