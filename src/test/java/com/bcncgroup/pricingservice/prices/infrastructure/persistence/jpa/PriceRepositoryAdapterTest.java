package com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa;

import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa.mappers.EntityToPriceMapper;
import com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa.models.PriceEntity;
import com.bcncgroup.pricingservice.shared.domain.exceptions.PriceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryAdapterTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private EntityToPriceMapper mapper;

    @Mock
    private TypedQuery<PriceEntity> typedQuery;

    @InjectMocks
    private PriceRepositoryAdapter adapter;

    private PriceEntity priceEntity;

    @BeforeEach
    void setup() {
        priceEntity = new PriceEntity();
        priceEntity.setBrandId(1L);
        priceEntity.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
        priceEntity.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        priceEntity.setPriceList(1L);
        priceEntity.setProductId(35455L);
        priceEntity.setPriority(0L);
        priceEntity.setPrice(new BigDecimal("35.50"));
        priceEntity.setCurrency("EUR");
    }

    @Test
    void findPrice_withMatch_returnsMappedPrice() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);

        when(entityManager.createQuery(anyString(), eq(PriceEntity.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.setMaxResults(anyInt())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Collections.singletonList(priceEntity));

        Price expected = new Price(1L, priceEntity.getStartDate(), priceEntity.getEndDate(), 1L, 35455L, 0L,
                new BigDecimal("35.50"), "EUR");
        when(mapper.toPrice(priceEntity)).thenReturn(expected);

        Price actual = adapter.findPrice(date, 35455L, 1L);

        assertEquals(expected, actual);
        verify(entityManager).createQuery(anyString(), eq(PriceEntity.class));
    }

    @Test
    void findPrice_withNoResult_throwsPriceNotFoundException() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);

        when(entityManager.createQuery(anyString(), eq(PriceEntity.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.setMaxResults(anyInt())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Collections.emptyList());

        assertThrows(PriceNotFoundException.class, () -> adapter.findPrice(date, 35455L, 1L));
        verify(entityManager).createQuery(anyString(), eq(PriceEntity.class));
    }

}
