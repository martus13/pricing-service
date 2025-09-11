package com.bcncgroup.pricingservice.prices.application.port.out;

import com.bcncgroup.pricingservice.prices.domain.Price;
import java.time.Instant;
import java.util.Optional;

/**
 * Outbound port that exposes the operation to load an applicable price from the
 * persistence layer.
 */
public interface LoadPricePort {
    /**
     * Loads an applicable price for the given instant, product and brand.
     *
     * @param applicationDate instant to evaluate
     * @param productId       product identifier
     * @param brandId         brand identifier
     * @return optional domain {@link Price} when found
     */
    Optional<Price> loadApplicablePrice(Instant applicationDate, Long productId, Long brandId);
}
