package com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa;

import com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa.models.PriceEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("select p from PriceEntity p " +
            "where :applicationDate between p.startDate and p.endDate and p.productId = :productId and p.brandId = :brandId " +
            "order by p.priority DESC")
    List<PriceEntity> findApplicablePrices(
            @Param("applicationDate") Instant applicationDate,
            @Param("productId") Long productId,
            @Param("brandId") Long brandId,
            Pageable pageable);
}
