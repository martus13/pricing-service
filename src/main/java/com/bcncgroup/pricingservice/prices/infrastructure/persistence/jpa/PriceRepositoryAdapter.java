package com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa;

import com.bcncgroup.pricingservice.prices.application.port.out.LoadPricePort;
import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa.mappers.EntityToPriceMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PriceRepositoryAdapter implements LoadPricePort {

    private final EntityToPriceMapper entityToPriceMapper;
    private final JpaPriceRepository jpaPriceRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Price> loadApplicablePrice(Instant applicationDate, Long productId, Long brandId) {
        Pageable p = PageRequest.of(0, 1);
        return jpaPriceRepository.findApplicablePrices(applicationDate, productId, brandId, p).stream()
                .findFirst()
                .map(entityToPriceMapper::toPrice);
    }

}
