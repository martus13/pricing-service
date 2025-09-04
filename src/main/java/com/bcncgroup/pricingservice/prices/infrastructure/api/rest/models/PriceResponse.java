package com.bcncgroup.pricingservice.prices.infrastructure.api.rest.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class PriceResponse {
    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long priceList;
    private Long productId;
    private Long priority;
    private BigDecimal price;
    private String currency;
}
