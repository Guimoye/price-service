package com.testjava2026.prices.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public record Price(
		Long brandId,
		Long productId,
		Integer priceList,
		LocalDateTime startDate,
		LocalDateTime endDate,
		BigDecimal price,
		String currency) {

	public Price {
		Objects.requireNonNull(price, "El precio no puede ser nulo");
		if (currency == null || currency.isBlank()) {
			throw new IllegalArgumentException("La moneda no puede estar vacía");
		}
	}
}
