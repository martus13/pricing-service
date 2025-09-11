package com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa.mappers;

import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa.models.PriceEntity;
import org.mapstruct.Mapper;

/**
 * Maps {@link PriceEntity} to {@link Price} domain objects.
 */
@Mapper(componentModel = "spring")
public interface EntityToPriceMapper {
    
    /**
     * Maps a {@link PriceEntity} to a {@link Price}.
     *
     * @param priceEntity JPA entity.
     * @return Domain object.
     */
    Price toPrice(PriceEntity priceEntity);
}
