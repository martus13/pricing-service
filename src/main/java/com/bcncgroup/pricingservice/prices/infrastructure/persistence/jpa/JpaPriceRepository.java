package com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa;

import com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa.models.PriceEntity;
import java.time.Instant;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for {@link PriceEntity}.
 */
@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    /**
     * Finds prices for the given date, product, and brand, ordered by priority descending.
     *
     * @param applicationDate Date to check.
     * @param productId Product identifier.
     * @param brandId Brand identifier.
     * @param pageable Pagination.
     * @return List of matching {@link PriceEntity}.
     */
    @Query(
            "select p from PriceEntity p "
                    + "where :applicationDate between p.startDate and p.endDate "
                    + "and p.productId = :productId "
                    + "and p.brandId = :brandId "
                    + "order by p.priority DESC")
    List<PriceEntity> findApplicablePrices(
            @Param("applicationDate") Instant applicationDate,
            @Param("productId") Long productId,
            @Param("brandId") Long brandId,
            Pageable pageable);
}
