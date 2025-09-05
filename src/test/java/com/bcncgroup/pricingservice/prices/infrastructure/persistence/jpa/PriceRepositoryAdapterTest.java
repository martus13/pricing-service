package com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa;

import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa.mappers.EntityToPriceMapper;
import com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa.models.PriceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryAdapterTest {

    @Mock
    private EntityToPriceMapper mapper;

    @Mock
    private JpaPriceRepository jpaPriceRepository;

    @InjectMocks
    private PriceRepositoryAdapter adapter;

    private PriceEntity priceEntity;
    private Price expectedPrice;

    @BeforeEach
    void setUp() {
        priceEntity = new PriceEntity(
                1L,
                1L,
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                1L,
                35455L,
                0,
                new BigDecimal("35.50"),
                "EUR");

        expectedPrice = new Price(
                priceEntity.getPriceList(),
                priceEntity.getStartDate(),
                priceEntity.getEndDate(),
                priceEntity.getBrandId(),
                priceEntity.getProductId(),
                priceEntity.getPriority(),
                priceEntity.getPrice(),
                priceEntity.getCurrency());
    }

    @Test
    void findPrice_shouldReturnMappedPrice_whenEntityExists() {
        // Arrange
        var limit1 = PageRequest.of(0, 1);
        var applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        when(jpaPriceRepository.findApplicablePrices(applicationDate, priceEntity.getProductId(), priceEntity
                .getBrandId(), limit1)).thenReturn(List.of(priceEntity));
        when(mapper.toPrice(priceEntity)).thenReturn(expectedPrice);

        // Act
        var result = adapter.loadApplicablePrice(applicationDate, priceEntity.getProductId(), priceEntity.getBrandId());

        // Assert
        assertThat(result).isPresent()
                .contains(expectedPrice);

        verify(jpaPriceRepository).findApplicablePrices(applicationDate, priceEntity.getProductId(),
                priceEntity.getBrandId(), limit1);
        verify(mapper).toPrice(priceEntity);
    }
}
