package com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa;

import com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa.models.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("select p from PriceEntity p " +
            "where :applicationDate between p.startDate and p.endDate and p.productId = :productId and p.brandId = :brandId " +
            "order by p.priority DESC")
    List<PriceEntity> findApplicablePrice(LocalDateTime applicationDate, Long productId, Long brandId);
}
