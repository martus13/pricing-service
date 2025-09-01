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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceService priceService;

    @Test
    void findPrice_delegatesToRepository_and_returnsPrice() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        Price expected = new Price(1L, date.minusDays(1), date.plusDays(1), 1L, 35455L, 0L, new BigDecimal("35.50"),
                "EUR");

        when(priceRepository.findPrice(date, 35455L, 1L)).thenReturn(expected);

        Price actual = priceService.findPrice(date, 35455L, 1L);

        assertEquals(expected, actual);
        verify(priceRepository).findPrice(date, 35455L, 1L);
    }

    @Test
    void findPrice_whenRepositoryThrows_propagates() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);

        when(priceRepository.findPrice(date, 35455L, 1L)).thenThrow(new PriceNotFoundException("not found"));

        assertThrows(PriceNotFoundException.class, () -> priceService.findPrice(date, 35455L, 1L));
        verify(priceRepository).findPrice(date, 35455L, 1L);
    }

}
