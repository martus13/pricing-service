package com.bcncgroup.pricingservice.prices.domain;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PriceRepository {
    Price findPrice(LocalDateTime applicationDate, Long productId, Long brandId);
}
