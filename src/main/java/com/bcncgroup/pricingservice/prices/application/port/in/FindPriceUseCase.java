package com.bcncgroup.pricingservice.prices.application.port.in;

import com.bcncgroup.pricingservice.prices.domain.Price;

import java.time.Instant;

public interface FindPriceUseCase {
    Price findPrice(Instant applicationDate, Long productId, Long brandId);
}
