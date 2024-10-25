package com.capitole.store.infrastructure.rest.unit;

import com.capitole.store.application.services.PriceService;
import com.capitole.store.domain.model.Price;
import com.capitole.store.infrastructure.rest.PriceController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(PriceController.class)
public class PriceControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    private DateTimeFormatter formatter;

    @BeforeEach
    public void setUp() {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
    }

    @Test
    public void testGetPriceAt10AMOn14th() throws Exception {
        LocalDateTime dateTime = LocalDateTime.parse("2020-06-14-10.00.00", formatter);
        Price priceMock = createPriceMock(35.50, dateTime, dateTime.plusDays(180)); // Set the start and end date

        when(priceService.getPrice(35455, 1, dateTime)).thenReturn(Optional.of(priceMock));

        performGetPriceTest("35455", "1", "2020-06-14-10.00.00", priceMock);
    }

    @Test
    public void testGetPriceAt4PMOn14th() throws Exception {
        LocalDateTime dateTime = LocalDateTime.parse("2020-06-14-16.00.00", formatter);
        Price priceMock = createPriceMock(25.45, dateTime.minusHours(1), dateTime.plusHours(2)); // Set the start and end date

        when(priceService.getPrice(35455, 1, dateTime)).thenReturn(Optional.of(priceMock));

        performGetPriceTest("35455", "1", "2020-06-14-16.00.00", priceMock);
    }

    @Test
    public void testGetPriceAt9PMOn14th() throws Exception {
        LocalDateTime dateTime = LocalDateTime.parse("2020-06-14-21.00.00", formatter);
        Price priceMock = createPriceMock(35.50, dateTime.minusDays(1), dateTime.plusDays(167)); // Set the start and end date

        when(priceService.getPrice(35455, 1, dateTime)).thenReturn(Optional.of(priceMock));

        performGetPriceTest("35455", "1", "2020-06-14-21.00.00", priceMock);
    }

    @Test
    public void testGetPriceAt10AMOn15th() throws Exception {
        LocalDateTime dateTime = LocalDateTime.parse("2020-06-15-10.00.00", formatter);
        Price priceMock = createPriceMock(30.50, dateTime.minusDays(1), dateTime.plusHours(1)); // Set the start and end date

        when(priceService.getPrice(35455, 1, dateTime)).thenReturn(Optional.of(priceMock));

        performGetPriceTest("35455", "1", "2020-06-15-10.00.00", priceMock);
    }

    @Test
    public void testGetPriceAt9PMOn16th() throws Exception {
        LocalDateTime dateTime = LocalDateTime.parse("2020-06-16-21.00.00", formatter);
        Price priceMock = createPriceMock(38.95, dateTime.minusDays(1), dateTime.plusDays(197)); // Set the start and end date

        when(priceService.getPrice(35455, 1, dateTime)).thenReturn(Optional.of(priceMock));

        performGetPriceTest("35455", "1", "2020-06-16-21.00.00", priceMock);
    }

    private void performGetPriceTest(String productId, String brandId, String applicationDate, Price priceMock) throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/api/price")
                        .param("productId", productId)
                        .param("brandId", brandId)
                        .param("applicationDate", applicationDate))
                .andExpect(MockMvcResultMatchers.status().isOk());

        validateResponse(resultActions, priceMock);
    }

    private void validateResponse(ResultActions resultActions, Price priceMock) throws Exception {
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(priceMock.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(priceMock.getBrandId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(priceMock.getProductId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.priority").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.curr").isNotEmpty());
    }

    private Price createPriceMock(double price, LocalDateTime startDate, LocalDateTime endDate) {
        Price priceMock = mock(Price.class);
        when(priceMock.getPrice()).thenReturn(price);
        when(priceMock.getBrandId()).thenReturn(1);
        when(priceMock.getProductId()).thenReturn(35455);
        when(priceMock.getStartDate()).thenReturn(startDate);
        when(priceMock.getEndDate()).thenReturn(endDate);
        when(priceMock.getPriceList()).thenReturn(1);
        when(priceMock.getPriority()).thenReturn(1);
        when(priceMock.getCurr()).thenReturn("EUR");
        return priceMock;
    }
}