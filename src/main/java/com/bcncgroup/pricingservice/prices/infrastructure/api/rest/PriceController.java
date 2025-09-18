package com.bcncgroup.pricingservice.prices.infrastructure.api.rest;

import com.bcncgroup.pricingservice.prices.application.port.in.FindPriceUseCase;
import com.bcncgroup.pricingservice.prices.infrastructure.api.rest.mappers.PriceToResponseMapper;
import com.bcncgroup.pricingservice.prices.infrastructure.api.rest.models.PriceResponse;
import java.time.OffsetDateTime;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
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
@Slf4j
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
    @GetMapping
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime applicationDate,
            @RequestParam @Positive Long productId,
            @RequestParam @Positive Long brandId) {

        log.debug("GET /prices?applicationDate={}&&productId={}&&brandId={}", applicationDate, productId, brandId);

        return ResponseEntity.ok(
                priceToResponseMapper.toResponse(
                        findPriceUseCase.findPrice(applicationDate.toInstant(), productId, brandId)));
    }
}
