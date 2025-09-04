package com.bcncgroup.pricingservice.prices.application;

import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.prices.domain.PriceRepository;
import com.bcncgroup.pricingservice.shared.domain.exceptions.PriceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindPriceUseCaseTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private FindPriceUseCase findPriceUseCase;

    @Test
    void findPrice_shouldReturnPrice_whenRepositoryReturnsResult() {
        // Arrange
        var applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        var productId = 35455L;
        var brandId = 1L;
        var expectedPrice = new Price(
                1L,
                applicationDate.minusDays(1),
                applicationDate.plusDays(1),
                brandId,
                productId,
                0L,
                new BigDecimal("35.50"),
                "EUR");

        when(priceRepository.findPrice(applicationDate, productId, brandId))
                .thenReturn(Optional.of(expectedPrice));

        // Act
        var actualPrice = findPriceUseCase.findPrice(applicationDate, productId, brandId);

        // Assert
        assertEquals(expectedPrice, actualPrice, "The returned price should match the expected one");
        verify(priceRepository).findPrice(applicationDate, productId, brandId);
    }

    @Test
    void findPrice_shouldThrowPriceNotFoundException_whenRepositoryThrows() {
        // Arrange
        var applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        var productId = 35455L;
        var brandId = 1L;

        when(priceRepository.findPrice(applicationDate, productId, brandId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PriceNotFoundException.class,
                () -> findPriceUseCase.findPrice(applicationDate, productId, brandId),
                "Should throw PriceNotFoundException when price is not found");

        verify(priceRepository).findPrice(applicationDate, productId, brandId);
    }
}
