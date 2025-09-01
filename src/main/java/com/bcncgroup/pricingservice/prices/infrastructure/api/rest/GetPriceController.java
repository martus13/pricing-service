package com.bcncgroup.pricingservice.prices.infrastructure.api.rest;

import com.bcncgroup.pricingservice.prices.application.PriceService;
import com.bcncgroup.pricingservice.prices.infrastructure.api.rest.models.PriceResponse;
import com.bcncgroup.pricingservice.prices.infrastructure.api.rest.mappers.PriceToResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.jbosslog.JBossLog;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@JBossLog
@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class GetPriceController {

    private final PriceService priceService;
    private final PriceToResponseMapper priceToResponseMapper;

    @GetMapping
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam Long productId,
            @RequestParam Long brandId) {

        log.debugv("GET /prices?productId={0}&&brandId={1}&&applicationDate={2}", productId, brandId, applicationDate);
        return ResponseEntity
                .ok(priceToResponseMapper.toResponse(priceService.findPrice(applicationDate, productId, brandId)));
    }
}
