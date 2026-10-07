package com.testjava2026.prices.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Price(
		Long brandId,
		Long productId,
		Integer priceList,
		LocalDateTime startDate,
		LocalDateTime endDate,
		BigDecimal price,
		String currency) {

	public Price {
		requireNotNull(brandId, "El identificador de la cadena no puede ser nulo");
		requireNotNull(productId, "El identificador del producto no puede ser nulo");
		requireNotNull(priceList, "La tarifa no puede ser nula");
		requireNotNull(startDate, "La fecha de inicio no puede ser nula");
		requireNotNull(endDate, "La fecha de fin no puede ser nula");
		requireNotNull(price, "El precio no puede ser nulo");
		if (endDate.isBefore(startDate)) {
			throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
		}
		if (price.signum() < 0) {
			throw new IllegalArgumentException("El precio no puede ser negativo");
		}
		if (currency == null || currency.isBlank()) {
			throw new IllegalArgumentException("La moneda no puede estar vacía");
		}
	}

	private static void requireNotNull(Object value, String message) {
		if (value == null) {
			throw new IllegalArgumentException(message);
		}
	}
}
