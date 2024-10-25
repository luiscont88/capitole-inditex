package com.capitole.store.domain.repository;

import com.capitole.store.domain.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql({"/data-test.sql"})
public class PriceRepositoryTest {

    @Autowired
    private PriceRepository priceRepository;

    private DateTimeFormatter formatter;

    @BeforeEach
    public void setUp() {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
    }

    @Test
    public void testFindPriceAtNoonOn14th() {
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14-00.00.00", formatter);
        List<Price> prices = priceRepository.findRelevantPrices(35455, 1, applicationDate, applicationDate);
        assertThat(prices).isNotEmpty();
        assertThat(prices.get(0).getPrice()).isEqualTo(35.50);
    }

    @Test
    public void testFindPriceAtEveningOn14th() {
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14-16.00.00", formatter);
        List<Price> prices = priceRepository.findRelevantPrices(35455, 1, applicationDate, applicationDate);
        assertThat(prices).isNotEmpty();
        assertThat(prices.get(0).getPrice()).isEqualTo(25.45);
    }

    @Test
    public void testFindPriceAtMorningOn15th() {
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-15-10.00.00", formatter);
        List<Price> prices = priceRepository.findRelevantPrices(35455, 1, applicationDate, applicationDate);
        assertThat(prices).isNotEmpty();
        assertThat(prices.get(0).getPrice()).isEqualTo(30.50);
    }

    @Test
    public void testFindPriceAtAfternoonOn15th() {
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-15-17.00.00", formatter);
        List<Price> prices = priceRepository.findRelevantPrices(35455, 1, applicationDate, applicationDate);
        assertThat(prices).isNotEmpty();
        assertThat(prices.get(0).getPrice()).isEqualTo(38.95);
    }
}
