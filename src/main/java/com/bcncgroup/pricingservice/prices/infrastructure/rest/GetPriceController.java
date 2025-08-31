package com.bcncgroup.pricingservice.prices.infrastructure.rest;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcncgroup.pricingservice.prices.application.PriceService;
import com.bcncgroup.pricingservice.prices.domain.Price;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class GetPriceController {

    private final PriceService priceService;

    @GetMapping
    public ResponseEntity<Price> getPrice(
            @RequestParam LocalDateTime applicationDate,
            @RequestParam Long productId,
            @RequestParam Long brandId) {

        return ResponseEntity.ok(priceService.execute(applicationDate, productId, brandId));
    }
}
