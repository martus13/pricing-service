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
    void given_applicationDate_within_priceList1_when_getPrices_then_return_priceList1() throws Exception {
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
    void given_applicationDate_within_priceList2_when_getPrices_then_return_priceList2() throws Exception {
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
    void given_applicationDate_within_priceList1_at_night_when_getPrices_then_return_priceList1() throws Exception {
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
    void given_applicationDate_within_priceList3_when_getPrices_then_return_priceList3() throws Exception {
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
    void given_applicationDate_within_priceList4_when_getPrices_then_return_priceList4() throws Exception {
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

    @Test
    @DisplayName("When no price found returns ProblemDetail 404")
    void given_noPrice_when_getPrices_then_return_ProblemDetail_404() throws Exception {
        // Arrange
        String applicationDate = "2020-01-01T00:00:00";
        String nonExistingProductId = "999999";

        // Act
        ResultActions result = mockMvc.perform(get("/prices")
                .param("applicationDate", applicationDate)
                .param("productId", nonExistingProductId)
                .param("brandId", String.valueOf(BRAND_ID)));

        // Assert
        result.andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is("404")))
                .andExpect(jsonPath("$.title", is("Price Not Found")))
                .andExpect(jsonPath("$.detail", is("Price not found")));
    }
}
