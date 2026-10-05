package com.testjava2026.prices.infrastructure.adapter.in.rest;

import com.testjava2026.prices.application.port.in.GetApplicablePriceUseCase;
import com.testjava2026.prices.infrastructure.adapter.in.rest.dto.PriceResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/prices")
public class PriceController {

	private final GetApplicablePriceUseCase getApplicablePriceUseCase;

	public PriceController(GetApplicablePriceUseCase getApplicablePriceUseCase) {
		this.getApplicablePriceUseCase = getApplicablePriceUseCase;
	}

	@GetMapping
	public PriceResponse getApplicablePrice(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
			@RequestParam Long brandId,
			@RequestParam Long productId) {
		return PriceResponse.from(getApplicablePriceUseCase.getApplicablePrice(applicationDate, brandId, productId));
	}
}
