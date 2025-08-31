package com.bcncgroup.pricingservice.prices.infrastructure.api.mapper;

import org.mapstruct.Mapper;

import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.prices.infrastructure.api.dto.PriceResponse;

@Mapper(componentModel = "spring")
public interface PriceToResponseMapper {
    PriceResponse toResponse(Price price);
}
