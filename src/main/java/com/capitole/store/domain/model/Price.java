package com.capitole.store.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "brand_id")
    private Integer brandId;

    @NotNull
    @FutureOrPresent(message = "startDate should be in the present or future")
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @NotNull
    @Future(message = "endDate should be in the future")
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @NotNull
    @Column(name = "price_list")
    private Integer priceList;

    @NotNull
    @Column(name = "product_id")
    private Integer productId;

    @NotNull
    @Column(name = "priority")
    private Integer priority;

    @NotNull
    @Positive(message = "price should be positive")
    @Column(name = "price")
    private Double price;

    @NotNull
    @Size(min = 3, max = 3, message = "curr should be a 3-letter currency code")
    @Column(name = "curr")
    private String curr;

}