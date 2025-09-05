package com.bcncgroup.pricingservice.prices.domain;

import java.math.BigDecimal;
import java.time.Instant;

public record Price(
                Long brandId,
                Instant startDate,
                Instant endDate,
                Long priceList,
                Long productId,
                Integer priority,
                BigDecimal price,
                String currency) {

}
