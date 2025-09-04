package com.bcncgroup.pricingservice.prices.domain;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<Price> findPrice(LocalDateTime applicationDate, Long productId, Long brandId);
}
