package com.bcncgroup.pricingservice.prices.application;

import com.bcncgroup.pricingservice.prices.application.port.in.FindPriceUseCase;
import com.bcncgroup.pricingservice.prices.application.port.out.LoadPricePort;
import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.shared.domain.exceptions.PriceNotFoundException;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

/**
 * Application service for finding applicable prices.
 * Implements {@link FindPriceUseCase}.
 */
@Service
@RequiredArgsConstructor
public class PriceService implements FindPriceUseCase {

    private final LoadPricePort loadPricePort;

    /**
     * Finds the applicable price for the given date, product, and brand.
     *
     * @param applicationDate Date and time to apply the price.
     * @param productId Product identifier.
     * @param brandId Brand identifier.
     * @return The applicable {@link Price}.
     * @throws PriceNotFoundException if no price is found.
     */
    @Override
    @Cacheable(value = "prices", key = "#brandId + '-' + #productId + '-' + #applicationDate.toString()")
    public Price findPrice(Instant applicationDate, Long productId, Long brandId) {
        return loadPricePort.loadApplicablePrice(applicationDate, productId, brandId)
                .orElseThrow(() -> new PriceNotFoundException(
                        String.format("Price not found for applicationDate=%s, productId=%s, brandId=%s",
                                applicationDate, productId, brandId)));
    }
}
