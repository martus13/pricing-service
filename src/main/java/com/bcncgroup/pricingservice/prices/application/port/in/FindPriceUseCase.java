package com.bcncgroup.pricingservice.prices.application.port.in;

import com.bcncgroup.pricingservice.prices.domain.Price;

import java.time.LocalDateTime;

public interface FindPriceUseCase {
    Price findPrice(LocalDateTime applicationDate, Long productId, Long brandId);
}
