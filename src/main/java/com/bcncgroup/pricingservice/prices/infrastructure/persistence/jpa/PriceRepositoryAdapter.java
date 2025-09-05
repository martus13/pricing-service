package com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa;

import com.bcncgroup.pricingservice.prices.application.port.out.LoadPricePort;
import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa.mappers.EntityToPriceMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PriceRepositoryAdapter implements LoadPricePort {

    private final EntityToPriceMapper entityToPriceMapper;
    private final JpaPriceRepository jpaPriceRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Price> loadApplicablePrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        return jpaPriceRepository.findApplicablePrices(applicationDate, productId, brandId, PageRequest.of(0, 1)).stream()
                .findFirst()
                .map(entityToPriceMapper::toPrice);
    }

}
