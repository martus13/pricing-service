package com.bcncgroup.pricingservice.prices.infrastructure.persistence.h2.mappers;

import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.prices.infrastructure.persistence.h2.models.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityToPriceMapper {
    Price toPrice(PriceEntity priceEntity);
}
