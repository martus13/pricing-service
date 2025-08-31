package com.bcncgroup.pricingservice.prices.infrastructure.database.h2;

import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.prices.domain.PriceRepository;
import com.bcncgroup.pricingservice.prices.infrastructure.database.h2.model.PriceEntity;
import com.bcncgroup.pricingservice.prices.infrastructure.database.h2.model.PriceEntityMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class H2RepositoryAdapter implements PriceRepository {

        @PersistenceContext
        private final EntityManager entityManager;

        private final PriceEntityMapper priceEntityMapper;

        @Override
        public Price findPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
                return priceEntityMapper.toPrice(
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
