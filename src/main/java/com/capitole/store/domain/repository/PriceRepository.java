package com.capitole.store.domain.repository;

import com.capitole.store.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE p.productId = :productId AND p.brandId = :brandId AND p.startDate <= :startDate AND p.endDate >= :endDate ORDER BY p.priority DESC")
    List<Price> findRelevantPrices(@Param("productId") int productId,
                                   @Param("brandId") int brandId,
                                   @Param("startDate") LocalDateTime startDate,
                                   @Param("endDate") LocalDateTime endDate);

}
