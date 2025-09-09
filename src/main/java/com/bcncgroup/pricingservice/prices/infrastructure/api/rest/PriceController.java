package com.bcncgroup.pricingservice.prices.infrastructure.api.rest;

import com.bcncgroup.pricingservice.prices.application.port.in.FindPriceUseCase;
import com.bcncgroup.pricingservice.prices.infrastructure.api.rest.mappers.PriceToResponseMapper;
import com.bcncgroup.pricingservice.prices.infrastructure.api.rest.models.PriceResponse;
import com.bcncgroup.pricingservice.shared.domain.exceptions.PriceBadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.jbosslog.JBossLog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

@JBossLog
@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PriceController {

    private final FindPriceUseCase findPriceUseCase;
    private final PriceToResponseMapper priceToResponseMapper;

    @GetMapping("/products/{productId}/brands/{brandId}")
    public ResponseEntity<PriceResponse> getPrice(
            @PathVariable Long productId,
            @PathVariable Long brandId,
            @RequestParam String applicationDate) {

        OffsetDateTime applicationDateTime = parseApplicationDate(applicationDate);

        log.debugv("GET /prices/products/%d/brands/%d?applicationDate=%s"
                .formatted(productId, brandId, applicationDateTime.toString()));
        return ResponseEntity
                .ok(priceToResponseMapper
                        .toResponse(findPriceUseCase.findPrice(applicationDateTime.toInstant(), productId, brandId)));
    }

    private OffsetDateTime parseApplicationDate(String applicationDate){
        try {
            return OffsetDateTime.parse(applicationDate);
        } catch (DateTimeParseException ex) {
            throw new PriceBadRequestException(String.format("Invalid applicationDate format %s", applicationDate));
        }
    }
}
