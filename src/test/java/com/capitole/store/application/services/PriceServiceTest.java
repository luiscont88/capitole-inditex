package com.capitole.store.application.services;

import com.capitole.store.domain.model.Price;
import com.capitole.store.domain.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceService;

    private DateTimeFormatter formatter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
    }

    @Test
    public void testGetPrice() {
        LocalDateTime dateTime = LocalDateTime.parse("2020-06-14-10.00.00", formatter);
        Price mockPrice = new Price();
        mockPrice.setProductId(35455);
        mockPrice.setBrandId(1);
        mockPrice.setStartDate(dateTime);
        mockPrice.setEndDate(dateTime.plusHours(2)); // Fecha válida

        when(priceRepository.findRelevantPrices(35455, 1, dateTime, dateTime)).thenReturn(Collections.singletonList(mockPrice));
        Optional<Price> price = priceService.getPrice(35455, 1, dateTime);
        assertThat(price).isPresent();
        assertThat(price.get().getProductId()).isEqualTo(35455);
        assertThat(price.get().getBrandId()).isEqualTo(1);
    }

    @Test
    public void testGetPriceWhenNotFound() {
        LocalDateTime dateTime = LocalDateTime.parse("2020-06-14-10.00.00", formatter);

        when(priceRepository.findRelevantPrices(35455, 1, dateTime, dateTime)).thenReturn(Collections.emptyList());
        Optional<Price> price = priceService.getPrice(35455, 1, dateTime);
        assertThat(price).isNotPresent();
    }

}