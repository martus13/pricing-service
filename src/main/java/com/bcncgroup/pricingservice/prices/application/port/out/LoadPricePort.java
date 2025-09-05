package com.bcncgroup.pricingservice.prices.application.port.out;

import com.bcncgroup.pricingservice.prices.domain.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface LoadPricePort {
    Optional<Price> loadApplicablePrice(LocalDateTime applicationDate, Long productId, Long brandId);
}
