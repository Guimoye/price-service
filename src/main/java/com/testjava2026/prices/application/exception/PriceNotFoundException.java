package com.testjava2026.prices.application.exception;

import java.time.LocalDateTime;

public class PriceNotFoundException extends RuntimeException {

	public PriceNotFoundException(Long brandId, Long productId, LocalDateTime applicationDate) {
		super("No hay tarifa aplicable para brandId=%d, productId=%d, fecha=%s"
				.formatted(brandId, productId, applicationDate));
	}
}
