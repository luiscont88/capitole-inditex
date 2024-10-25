package com.capitole.store.application.services;

import com.capitole.store.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {
    Optional<Price> getPrice(Integer productId, Integer brandId, LocalDateTime applicationDate);
}