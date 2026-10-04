package com.testjava2026.prices.application.port.out;

import com.testjava2026.prices.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {

	Optional<Price> findApplicablePrice(LocalDateTime applicationDate, Long brandId, Long productId);
}
