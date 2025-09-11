package com.bcncgroup.pricingservice.prices.infrastructure.api.rest.mappers;

import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.prices.infrastructure.api.rest.models.PriceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceToResponseMapper {
    /**
     * Maps a domain {@link Price} to an API {@link PriceResponse}.
     *
     * @param price domain price
     * @return API response model
     */
    PriceResponse toResponse(Price price);
}
