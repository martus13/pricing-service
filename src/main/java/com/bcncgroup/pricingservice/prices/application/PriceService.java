package com.bcncgroup.pricingservice.prices.application;

import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.prices.domain.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;

    public Price findPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        return priceRepository.findPrice(applicationDate, productId, brandId);
    }
}
