package com.capitole.store.application.services;

import com.capitole.store.domain.model.Price;
import com.capitole.store.domain.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Optional<Price> getPrice(Integer productId, Integer brandId, LocalDateTime applicationDate) {
        List<Price> prices = priceRepository.findRelevantPrices(productId, brandId, applicationDate, applicationDate);
        return prices.stream().findFirst();
    }

}