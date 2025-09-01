package com.bcncgroup.pricingservice.prices.infrastructure.api.rest.mappers;

import org.mapstruct.Mapper;

import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.prices.infrastructure.api.rest.models.PriceResponse;

@Mapper(componentModel = "spring")
public interface PriceToResponseMapper {
    PriceResponse toResponse(Price price);
}
