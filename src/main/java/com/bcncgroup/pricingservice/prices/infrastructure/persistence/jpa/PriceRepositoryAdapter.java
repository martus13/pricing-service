package com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa;

import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.prices.domain.PriceRepository;
import com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa.mappers.EntityToPriceMapper;
import com.bcncgroup.pricingservice.shared.domain.exceptions.PriceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
@AllArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {

    private final EntityToPriceMapper entityToPriceMapper;
    private final JpaPriceRepository jpaPriceRepository;

    @Override
    @Transactional(readOnly = true)
    public Price findPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        return jpaPriceRepository.findApplicablePrice(applicationDate, productId, brandId).stream()
                .findFirst()
                .map(entityToPriceMapper::toPrice)
                .orElseThrow(() -> new PriceNotFoundException("Price not found"));
    }

}
