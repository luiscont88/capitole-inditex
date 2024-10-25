package com.capitole.store.infrastructure.rest.integration;

import com.capitole.store.StoreApplication;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(classes = StoreApplication.class)
@AutoConfigureMockMvc
@Transactional
@Sql({"/data-test.sql"})
public class PriceControllerIntegrationTest {

    private static final String BASE_URL = "/api/price";
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPriceAt10AMOn14th() throws Exception {
        performGetPriceTest("35455", "1", "2020-06-14-10.00.00", 35.50);
    }

    @Test
    public void testGetPriceAt4PMOn14th() throws Exception {
        performGetPriceTest("35455", "1", "2020-06-14-16.00.00", 25.45);
    }

    @Test
    public void testGetPriceAt9PMOn14th() throws Exception {
        performGetPriceTest("35455", "1", "2020-06-14-21.00.00", 35.50);
    }

    @Test
    public void testGetPriceAt10AMOn15th() throws Exception {
        performGetPriceTest("35455", "1", "2020-06-15-10.00.00", 30.50);
    }

    @Test
    public void testGetPriceAt9PMOn16th() throws Exception {
        performGetPriceTest("35455", "1", "2020-06-16-21.00.00", 38.95);
    }

    /**
     * Helper method to perform GET request and validate the price.
     *
     * @param productId       the product ID
     * @param brandId         the brand ID
     * @param applicationDate the application date string
     * @param expectedPrice   the expected price for the test case
     * @throws Exception if there is an error during the request
     */
    private void performGetPriceTest(String productId, String brandId, String applicationDate, double expectedPrice) throws Exception {
        ResultActions resultActions = mockMvc.perform(get(BASE_URL)
                        .param("productId", productId)
                        .param("brandId", brandId)
                        .param("applicationDate", applicationDate))
                .andExpect(MockMvcResultMatchers.status().isOk());

        validateResponse(resultActions, expectedPrice);
    }

    /**
     * Helper method to validate the response.
     *
     * @param resultActions the ResultActions object
     * @param expectedPrice the expected price to validate
     * @throws Exception if there is an error during the validation
     */
    private void validateResponse(ResultActions resultActions, double expectedPrice) throws Exception {
        resultActions
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(expectedPrice))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.priority").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.curr").isNotEmpty());
    }

}