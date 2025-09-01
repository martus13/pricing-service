package com.bcncgroup.pricingservice.prices.infrastructure.api.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GetPriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final Long PRODUCT_ID = 35455L;
    private static final Long BRAND_ID = 1L; // ZARA
    private static final String CURRENCY = "EUR";

    private ResultActions performGet(String applicationDate) throws Exception {
        return mockMvc.perform(get("/prices")
                .param("applicationDate", applicationDate)
                .param("productId", String.valueOf(PRODUCT_ID))
                .param("brandId", String.valueOf(BRAND_ID)));
    }

    @Test
    @DisplayName("Test 1 - 2020-06-14T10:00: priceList=1 price=35.50")
    void given_2020_06_14T10_when_getPrice_then_return_priceList_1() throws Exception {
        // Arrange
        String applicationDate = "2020-06-14T10:00:00";

        // Act
        ResultActions result = performGet(applicationDate);

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(PRODUCT_ID.intValue())))
                .andExpect(jsonPath("$.brandId", is(BRAND_ID.intValue())))
                .andExpect(jsonPath("$.priceList", is(1)))
                .andExpect(jsonPath("$.price", is(35.5)))
                .andExpect(jsonPath("$.currency", is(CURRENCY)));
    }

    @Test
    @DisplayName("Test 2 - 2020-06-14T16:00: priceList=2 price=25.45")
    void given_2020_06_14T16_when_getPrice_then_return_priceList_2() throws Exception {
        // Arrange
        String applicationDate = "2020-06-14T16:00:00";

        // Act
        ResultActions result = performGet(applicationDate);

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(PRODUCT_ID.intValue())))
                .andExpect(jsonPath("$.brandId", is(BRAND_ID.intValue())))
                .andExpect(jsonPath("$.priceList", is(2)))
                .andExpect(jsonPath("$.price", is(25.45)))
                .andExpect(jsonPath("$.currency", is(CURRENCY)));
    }

    @Test
    @DisplayName("Test 3 - 2020-06-14T21:00: priceList=1 price=35.50")
    void given_2020_06_14T21_when_getPrice_then_return_priceList_1_again() throws Exception {
        // Arrange
        String applicationDate = "2020-06-14T21:00:00";

        // Act
        ResultActions result = performGet(applicationDate);

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(PRODUCT_ID.intValue())))
                .andExpect(jsonPath("$.brandId", is(BRAND_ID.intValue())))
                .andExpect(jsonPath("$.priceList", is(1)))
                .andExpect(jsonPath("$.price", is(35.5)))
                .andExpect(jsonPath("$.currency", is(CURRENCY)));
    }

    @Test
    @DisplayName("Test 4 - 2020-06-15T10:00: priceList=3 price=30.50")
    void given_2020_06_15T10_when_getPrice_then_return_priceList_3() throws Exception {
        // Arrange
        String applicationDate = "2020-06-15T10:00:00";

        // Act
        ResultActions result = performGet(applicationDate);

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(PRODUCT_ID.intValue())))
                .andExpect(jsonPath("$.brandId", is(BRAND_ID.intValue())))
                .andExpect(jsonPath("$.priceList", is(3)))
                .andExpect(jsonPath("$.price", is(30.5)))
                .andExpect(jsonPath("$.currency", is(CURRENCY)));
    }

    @Test
    @DisplayName("Test 5 - 2020-06-16T21:00: priceList=4 price=38.95")
    void given_2020_06_16T21_when_getPrice_then_return_priceList_4() throws Exception {
        // Arrange
        String applicationDate = "2020-06-16T21:00:00";

        // Act
        ResultActions result = performGet(applicationDate);

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(PRODUCT_ID.intValue())))
                .andExpect(jsonPath("$.brandId", is(BRAND_ID.intValue())))
                .andExpect(jsonPath("$.priceList", is(4)))
                .andExpect(jsonPath("$.price", is(38.95)))
                .andExpect(jsonPath("$.currency", is(CURRENCY)));
    }
}
