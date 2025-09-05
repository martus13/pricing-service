package com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa.mappers;

import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa.models.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityToPriceMapper {
    Price toPrice(PriceEntity priceEntity);
}
