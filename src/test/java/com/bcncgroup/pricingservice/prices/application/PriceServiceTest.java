package com.bcncgroup.pricingservice.prices.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.bcncgroup.pricingservice.prices.application.port.out.LoadPricePort;
import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.shared.domain.exceptions.PriceNotFoundException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private LoadPricePort loadPricePort;

    @InjectMocks
    private PriceService priceService;

    @Test
    void findPrice_shouldReturnPrice_whenRepositoryReturnsResult() {
        // Arrange
        var applicationDate = Instant.parse("2020-06-14T10:00:00Z");
        var productId = 35455L;
        var brandId = 1L;
        var expectedPrice =
                new Price(
                        1L,
                        applicationDate.minus(1, ChronoUnit.DAYS),
                        applicationDate.plus(1, ChronoUnit.DAYS),
                        brandId,
                        productId,
                        0,
                        new BigDecimal("35.50"),
                        "EUR");

        when(loadPricePort.loadApplicablePrice(applicationDate, productId, brandId))
                .thenReturn(Optional.of(expectedPrice));

        // Act
        var actualPrice = priceService.findPrice(applicationDate, productId, brandId);

        // Assert
        assertEquals(expectedPrice, actualPrice, "The returned price should match the expected one");
    }

    @Test
    void findPrice_shouldThrowPriceNotFoundException_whenRepositoryThrows() {
        // Arrange
        var applicationDate = Instant.parse("2020-06-14T10:00:00Z");
        var productId = 35455L;
        var brandId = 1L;

        when(loadPricePort.loadApplicablePrice(applicationDate, productId, brandId))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(
                PriceNotFoundException.class,
                () -> priceService.findPrice(applicationDate, productId, brandId),
                "Should throw PriceNotFoundException when price is not found");
    }
}
