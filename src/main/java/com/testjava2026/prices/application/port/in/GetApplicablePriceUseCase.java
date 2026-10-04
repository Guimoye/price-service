package com.testjava2026.prices.application.port.in;

import com.testjava2026.prices.domain.model.Price;

import java.time.LocalDateTime;

public interface GetApplicablePriceUseCase {

	Price getApplicablePrice(LocalDateTime applicationDate, Long brandId, Long productId);
}
