package com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa.mappers.EntityToPriceMapper;
import com.bcncgroup.pricingservice.prices.infrastructure.persistence.jpa.models.PriceEntity;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

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
        priceEntity =
                new PriceEntity(
                        1L,
                        1L,
                        Instant.parse("2020-06-14T10:00:00Z"),
                        Instant.parse("2020-12-31T23:59:59Z"),
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
        var applicationDate = Instant.parse("2020-06-14T10:00:00Z");
        when(jpaPriceRepository
                .findApplicablePrices(applicationDate, priceEntity.getProductId(), priceEntity.getBrandId(), limit1))
                .thenReturn(List.of(priceEntity));
        when(mapper.toPrice(priceEntity)).thenReturn(expectedPrice);

        // Act
        var result = adapter.loadApplicablePrice(applicationDate, priceEntity.getProductId(), priceEntity.getBrandId());

        // Assert
        assertThat(result).isPresent().contains(expectedPrice);

        verify(jpaPriceRepository)
                .findApplicablePrices(
                        applicationDate,
                        priceEntity.getProductId(),
                        priceEntity.getBrandId(),
                        limit1);
        verify(mapper).toPrice(priceEntity);
    }

    @Test
    void findPrice_shouldReturnEmpty_whenEntityDoesNotExist() {
        // Arrange
        var limit1 = PageRequest.of(0, 1);
        var applicationDate = Instant.parse("2020-06-14T10:00:00Z");
        when(jpaPriceRepository.findApplicablePrices(
                        applicationDate,
                        priceEntity.getProductId(),
                        priceEntity.getBrandId(),
                        limit1))
                .thenReturn(List.of());

        // Act
        var result =
                adapter.loadApplicablePrice(
                        applicationDate, priceEntity.getProductId(), priceEntity.getBrandId());

        // Assert
        assertThat(result).isEmpty();
        verify(jpaPriceRepository)
                .findApplicablePrices(
                        applicationDate,
                        priceEntity.getProductId(),
                        priceEntity.getBrandId(),
                        limit1);
    }
}
