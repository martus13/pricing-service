package com.bcncgroup.pricingservice.prices.infrastructure.api.rest.mappers;

import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.prices.infrastructure.api.rest.models.PriceResponse;
import org.mapstruct.Mapper;

/**
 * Maps {@link Price} domain objects to {@link PriceResponse} DTOs.
 */
@Mapper(componentModel = "spring")
public interface PriceToResponseMapper {

    /**
     * Maps a {@link Price} to a {@link PriceResponse}.
     *
     * @param price Domain object.
     * @return DTO.
     */
    PriceResponse toResponse(Price price);
}
