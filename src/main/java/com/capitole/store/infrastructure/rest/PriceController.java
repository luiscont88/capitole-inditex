package com.capitole.store.infrastructure.rest;

import com.capitole.store.application.services.PriceService;
import com.capitole.store.domain.model.Price;
import com.capitole.store.infrastructure.rest.dto.PriceResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/api/price")
    @Operation(summary = "Retrieve price.", description = "Retrieve product price by productId, brandId and applicationDate.")
    public ResponseEntity<PriceResponseDTO> getPrice(
            @RequestParam Integer productId,
            @RequestParam Integer brandId,
            @RequestParam String applicationDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        LocalDateTime dateTime = LocalDateTime.parse(applicationDate, formatter);

        Optional<Price> priceOptional = priceService.getPrice(productId, brandId, dateTime);
        return priceOptional.map(price -> {
            PriceResponseDTO responseDTO = toPriceResponseDTO(price);
            return ResponseEntity.ok(responseDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    private PriceResponseDTO toPriceResponseDTO(Price price) {
        PriceResponseDTO dto = new PriceResponseDTO();
        dto.setId(price.getId());
        dto.setBrandId(price.getBrandId());
        dto.setStartDate(price.getStartDate());
        dto.setEndDate(price.getEndDate());
        dto.setPriceList(price.getPriceList());
        dto.setProductId(price.getProductId());
        dto.setPriority(price.getPriority());
        dto.setPrice(price.getPrice());
        dto.setCurr(price.getCurr());
        return dto;
    }

}