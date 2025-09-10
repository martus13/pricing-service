package com.bcncgroup.pricingservice.prices.application.port.out;

import com.bcncgroup.pricingservice.prices.domain.Price;
import java.time.Instant;
import java.util.Optional;

public interface LoadPricePort {
    Optional<Price> loadApplicablePrice(Instant applicationDate, Long productId, Long brandId);
}
