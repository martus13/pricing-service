package com.bcncgroup.pricingservice.prices.infrastructure.persistence.h2.model;

import com.bcncgroup.pricingservice.prices.domain.Price;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityToPriceMapper {
    Price toPrice(PriceEntity priceEntity);
}
