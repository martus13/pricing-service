package com.bcncgroup.pricingservice.prices.infrastructure.database.h2.model;

import org.mapstruct.Mapper;

import com.bcncgroup.pricingservice.prices.domain.Price;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {
    Price toPrice(PriceEntity priceEntity);
}
