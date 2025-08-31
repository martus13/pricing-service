package com.bcncgroup.pricingservice.prices.infrastructure.persistence.h2;

import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.prices.domain.PriceRepository;
import com.bcncgroup.pricingservice.prices.infrastructure.persistence.h2.model.PriceEntity;
import com.bcncgroup.pricingservice.prices.infrastructure.persistence.h2.model.EntityToPriceMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
@AllArgsConstructor
public class H2RepositoryAdapter implements PriceRepository {

        @PersistenceContext
        private final EntityManager entityManager;

        private final EntityToPriceMapper entityToPriceMapper;

        @Override
        @Transactional(readOnly = true)
        public Price findPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
                return entityToPriceMapper.toPrice(
                        entityManager
                                .createQuery(
                                        "select p from PriceEntity p where :applicationDate between p.startDate and p.endDate and p.productId = :productId and p.brandId = :brandId order by p.priority DESC",
                                        PriceEntity.class)
                                .setParameter("applicationDate", applicationDate)
                                .setParameter("productId", productId)
                                .setParameter("brandId", brandId)
                                .setMaxResults(1)
                                .getSingleResult());
        }

}
