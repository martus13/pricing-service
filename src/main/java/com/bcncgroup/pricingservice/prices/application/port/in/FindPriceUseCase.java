package com.bcncgroup.pricingservice.prices.application.port.in;

import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.shared.domain.exceptions.PriceNotFoundException;

import java.time.Instant;

/**
 * Use case for finding the applicable price.
 */
public interface FindPriceUseCase {

    /**
     * Finds the applicable price for the given date, product, and brand.
     *
     * @param applicationDate Date and time to apply the price.
     * @param productId Product identifier.
     * @param brandId Brand identifier.
     * @return The applicable {@link Price}.
     * @throws PriceNotFoundException if no price is found.
     */
    Price findPrice(Instant applicationDate, Long productId, Long brandId);
}
