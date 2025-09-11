package com.bcncgroup.pricingservice.prices.infrastructure.api.rest;

import com.bcncgroup.pricingservice.prices.application.port.in.FindPriceUseCase;
import com.bcncgroup.pricingservice.prices.infrastructure.api.rest.mappers.PriceToResponseMapper;
import com.bcncgroup.pricingservice.prices.infrastructure.api.rest.models.PriceResponse;
import com.bcncgroup.pricingservice.shared.domain.exceptions.PriceBadRequestException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.jbosslog.JBossLog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Manages the RESTful API endpoints for querying product prices.
 *
 * <p>This controller acts as a <strong>primary adapter</strong> in the hexagonal architecture,
 * translating incoming HTTP requests into calls to the application's use cases (input ports).
 * It is responsible for request validation, invoking the core business logic, and mapping
 * the results back to an appropriate HTTP response.
 */
@JBossLog
@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PriceController {

    private final FindPriceUseCase findPriceUseCase;
    private final PriceToResponseMapper priceToResponseMapper;

    /**
     * Returns the applicable price for the given product, brand, and date.
     *
     * @param productId Product identifier.
     * @param brandId Brand identifier.
     * @param applicationDate Date and time in ISO-8601 format.
     * @return 200 OK with {@link PriceResponse} if found, 404 if not found, 400 if date is invalid.
     */
    @GetMapping("/products/{productId}/brands/{brandId}")
    public ResponseEntity<PriceResponse> getPrice(
            @PathVariable Long productId,
            @PathVariable Long brandId,
            @RequestParam String applicationDate) {

        OffsetDateTime applicationDateTime = parseApplicationDate(applicationDate);

        log.debugv("GET /prices/products/%d/brands/%d?applicationDate=%s"
                .formatted(productId, brandId, applicationDateTime.toString()));

        return ResponseEntity.ok(
                priceToResponseMapper.toResponse(
                        findPriceUseCase.findPrice(applicationDateTime.toInstant(), productId, brandId)));
    }

    private OffsetDateTime parseApplicationDate(String applicationDate) {
        try {
            return OffsetDateTime.parse(applicationDate);
        } catch (DateTimeParseException ex) {
            throw new PriceBadRequestException(
                    String.format("Invalid applicationDate format %s", applicationDate), ex);
        }
    }
}
