package com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa;

import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.prices.domain.PriceRepository;
import com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa.mappers.EntityToPriceMapper;
import com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa.models.PriceEntity;
import com.bcncgroup.pricingservice.shared.domain.exceptions.PriceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {

        @PersistenceContext
        private final EntityManager entityManager;

        private final EntityToPriceMapper entityToPriceMapper;

        @Override
        @Transactional(readOnly = true)
        public Price findPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
                List<PriceEntity> prices = entityManager
                        .createQuery(
                                "select p from PriceEntity p " +
                                "where :applicationDate between p.startDate and p.endDate " +
                                "and p.productId = :productId " +
                                "and p.brandId = :brandId " +
                                "order by p.priority DESC",
                                PriceEntity.class)
                        .setParameter("applicationDate", applicationDate)
                        .setParameter("productId", productId)
                        .setParameter("brandId", brandId)
                        .setMaxResults(1)
                        .getResultList();

                return prices.stream()
                        .findFirst()
                        .map(entityToPriceMapper::toPrice)
                        .orElseThrow(() -> new PriceNotFoundException("Price not found"));
        }

}
