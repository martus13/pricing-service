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

    /**
     * Find the applicable price for the given application date, product and brand.
     *
     * @param applicationDate instant of application
     * @param productId       id of the product
     * @param brandId         id of the brand
     * @return the applicable {@link Price}
     * @throws PriceNotFoundException if no price is found for the input criteria
     */
    @Override
    public Price findPrice(Instant applicationDate, Long productId, Long brandId) {
        return loadPricePort.loadApplicablePrice(applicationDate, productId, brandId)
                .orElseThrow(() -> new PriceNotFoundException(
                        String.format("Price not found for applicationDate=%s, productId=%s, brandId=%s",
                                applicationDate, productId, brandId)));
    }
}
