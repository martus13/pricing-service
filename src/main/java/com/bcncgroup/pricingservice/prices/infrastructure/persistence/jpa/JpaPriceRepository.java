package com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa;

import com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa.models.PriceEntity;
import java.time.Instant;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    /**
     * Query to find price entities that are applicable for the given application
     * date,
     * product and brand. Results are ordered by priority descending.
     *
     * @param applicationDate instant to check inclusion in the price validity
     *                        window
     * @param productId       product identifier
     * @param brandId         brand identifier
     * @param pageable        pagination configuration (expected page size of 1 when
     *                        used)
     * @return list of matching {@link PriceEntity}
     */
    @Query("select p from PriceEntity p "
            + "where :applicationDate between p.startDate and p.endDate and p.productId = :productId and p.brandId = :brandId "
            + "order by p.priority DESC")
    List<PriceEntity> findApplicablePrices(
            @Param("applicationDate") Instant applicationDate,
            @Param("productId") Long productId,
            @Param("brandId") Long brandId,
            Pageable pageable);
}
