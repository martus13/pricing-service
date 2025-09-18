package com.bcncgroup.pricingservice.prices.infrastructure.api.rest;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

    @Autowired private MockMvc mockMvc;

    private static final Long PRODUCT_ID = 35455L;
    private static final Long BRAND_ID = 1L;
    private static final String CURRENCY = "EUR";

    private ResultActions performGet(String applicationDate, Long productId) throws Exception {
        String url = "/prices";
        return mockMvc.perform(
                get(url)
                        .param("applicationDate", applicationDate)
                        .param("productId", productId.toString())
                        .param("brandId", BRAND_ID.toString())
        );
    }

    @Test
    void getPrice_shouldReturnPrice35_50_whenApplicationDateIs2020_06_14T10_00() throws Exception {
        // Arrange
        var applicationDate = "2020-06-14T10:00:00Z";

        // Act
        var result = performGet(applicationDate, PRODUCT_ID);

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(PRODUCT_ID.intValue())))
                .andExpect(jsonPath("$.brandId", is(BRAND_ID.intValue())))
                .andExpect(jsonPath("$.priceList", is(1)))
                .andExpect(jsonPath("$.price", is(35.5)))
                .andExpect(jsonPath("$.currency", is(CURRENCY)));
    }

    @Test
    void getPrice_shouldReturnPrice25_45_whenApplicationDateIs2020_06_14T16_00() throws Exception {
        // Arrange
        var applicationDate = "2020-06-14T16:00:00Z";

        // Act
        var result = performGet(applicationDate, PRODUCT_ID);

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(PRODUCT_ID.intValue())))
                .andExpect(jsonPath("$.brandId", is(BRAND_ID.intValue())))
                .andExpect(jsonPath("$.priceList", is(2)))
                .andExpect(jsonPath("$.price", is(25.45)))
                .andExpect(jsonPath("$.currency", is(CURRENCY)));
    }

    @Test
    void getPrice_shouldReturnPrice35_50_whenApplicationDateIs2020_06_14T21_00() throws Exception {
        // Arrange
        var applicationDate = "2020-06-14T21:00:00Z";

        // Act
        var result = performGet(applicationDate, PRODUCT_ID);

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList", is(1)))
                .andExpect(jsonPath("$.price", is(35.5)));
    }

    @Test
    void getPrice_shouldReturnPrice30_50_whenApplicationDateIs2020_06_15T10_00() throws Exception {
        // Arrange
        var applicationDate = "2020-06-15T10:00:00Z";

        // Act
        var result = performGet(applicationDate, PRODUCT_ID);

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList", is(3)))
                .andExpect(jsonPath("$.price", is(30.5)));
    }

    @Test
    void getPrice_shouldReturnPrice38_95_whenApplicationDateIs2020_06_16T21_00() throws Exception {
        // Arrange
        var applicationDate = "2020-06-16T21:00:00Z";

        // Act
        var result = performGet(applicationDate, PRODUCT_ID);

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList", is(4)))
                .andExpect(jsonPath("$.price", is(38.95)));
    }

    @Test
    void getPrice_shouldReturn404ProblemDetail_whenProductDoesNotExist() throws Exception {
        // Arrange
        var applicationDate = "2020-01-01T00:00:00Z";
        var nonExistingProductId = 999999L;

        // Act
        var result = performGet(applicationDate, nonExistingProductId);

        // Assert
        result.andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is("404")))
                .andExpect(jsonPath("$.title", is("Price Not Found")))
                .andExpect(
                        jsonPath(
                                "$.detail",
                                is(String.format("Price not found for applicationDate=%s, productId=%s, brandId=%s",
                                        applicationDate,
                                        nonExistingProductId,
                                        BRAND_ID))));
    }

    @Test
    void getPrice_shouldReturn400BadRequest_whenApplicationDateIsMalformed() throws Exception {
        // Arrange
        var malformedDate = "not-a-date";

        // Act
        var result = performGet(malformedDate, PRODUCT_ID);

        // Assert
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is("400")))
                .andExpect(jsonPath("$.title", is("Bad request")));
    }
}
