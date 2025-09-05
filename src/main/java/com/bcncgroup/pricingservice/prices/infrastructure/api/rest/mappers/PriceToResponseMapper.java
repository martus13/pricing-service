package com.bcncgroup.pricingservice.prices.infrastructure.api.rest.mappers;

import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.prices.infrastructure.api.rest.models.PriceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceToResponseMapper {

    PriceResponse toResponse(Price price);
}
