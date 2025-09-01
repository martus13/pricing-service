package com.bcncgroup.pricingservice.prices.domain;

import java.time.LocalDateTime;

public interface PriceRepository {
    Price findPrice(LocalDateTime applicationDate, Long productId, Long brandId);
}
