package com.bcncgroup.pricingservice.prices.application;

import com.bcncgroup.pricingservice.prices.application.port.in.FindPriceUseCase;
import com.bcncgroup.pricingservice.prices.application.port.out.LoadPricePort;
import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.shared.domain.exceptions.PriceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class PriceService implements FindPriceUseCase {

    private final LoadPricePort loadPricePort;

    public Price findPrice(Instant applicationDate, Long productId, Long brandId) {
        return loadPricePort.loadApplicablePrice(applicationDate, productId, brandId)
                .orElseThrow(() -> new PriceNotFoundException("Price not found"));
    }
}
