package com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa;

import com.bcncgroup.pricingservice.prices.application.port.out.LoadPricePort;
import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa.mappers.EntityToPriceMapper;
import java.time.Instant;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Persistence adapter for loading prices.
 */
@Repository
@AllArgsConstructor
public class PriceRepositoryAdapter implements LoadPricePort {

    private final EntityToPriceMapper entityToPriceMapper;
    private final JpaPriceRepository jpaPriceRepository;

    /**
     * Finds the applicable price for the given date, product, and brand.
     *
     * @param applicationDate Date and time to apply the price.
     * @param productId Product identifier.
     * @param brandId Brand identifier.
     * @return Optional with the applicable {@link Price}, or empty if not found.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Price> loadApplicablePrice(Instant applicationDate, Long productId, Long brandId) {
        Pageable p = PageRequest.of(0, 1);
        return jpaPriceRepository
                .findApplicablePrices(applicationDate, productId, brandId, p)
                .stream()
                .findFirst()
                .map(entityToPriceMapper::toPrice);
    }
}
