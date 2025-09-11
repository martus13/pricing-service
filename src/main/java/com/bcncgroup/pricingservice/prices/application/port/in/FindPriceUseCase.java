package com.bcncgroup.pricingservice.prices.application.port.in;

import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.shared.domain.exceptions.PriceNotFoundException;

import java.time.Instant;

/**
 * Defines the contract for the use case responsible for finding the applicable
 * price.
 * <p>
 * This interface acts as an <strong>input port</strong> in the application's
 * hexagonal architecture,
 * abstracting the core business logic from the delivery mechanisms (e.g., REST
 * controllers).
 * Implementations of this interface will contain the logic to determine the
 * correct price
 * based on the provided criteria.
 * </p>
 */
public interface FindPriceUseCase {

    /**
     * Finds the price that should be applied for a specific product from a brand at
     * a given moment.
     * <p>
     * If multiple prices match the criteria, the one with the highest priority is
     * returned.
     * </p>
     *
     * @param applicationDate The specific date and time to check for price
     *                        applicability.
     * @param productId       The identifier of the product.
     * @param brandId         The identifier of the brand.
     * @return The applicable {@link Price} domain object.
     * @throws PriceNotFoundException if no applicable price is found for the given
     *                                criteria.
     */
    Price findPrice(Instant applicationDate, Long productId, Long brandId);
}
