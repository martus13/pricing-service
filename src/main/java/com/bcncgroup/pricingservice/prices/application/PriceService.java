package com.bcncgroup.pricingservice.prices.application;

import com.bcncgroup.pricingservice.prices.application.port.in.FindPriceUseCase;
import com.bcncgroup.pricingservice.prices.application.port.out.LoadPricePort;
import com.bcncgroup.pricingservice.prices.domain.Price;
import com.bcncgroup.pricingservice.shared.domain.exceptions.PriceNotFoundException;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceService implements FindPriceUseCase {

    private final LoadPricePort loadPricePort;

    @Override
    public Price findPrice(Instant applicationDate, Long productId, Long brandId) {
        return loadPricePort.loadApplicablePrice(applicationDate, productId, brandId)
                .orElseThrow(() -> new PriceNotFoundException(
                        String.format("Price not found for applicationDate=%s, productId=%s, brandId=%s",
                                applicationDate, productId, brandId)));
    }
}
