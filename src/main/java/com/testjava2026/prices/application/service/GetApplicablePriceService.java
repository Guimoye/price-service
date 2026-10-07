package com.testjava2026.prices.application.service;

import com.testjava2026.prices.application.port.in.GetApplicablePriceUseCase;
import com.testjava2026.prices.application.port.out.PriceRepository;
import com.testjava2026.prices.domain.exception.PriceNotFoundException;
import com.testjava2026.prices.domain.model.Price;

import java.time.LocalDateTime;

public class GetApplicablePriceService implements GetApplicablePriceUseCase {

	private final PriceRepository priceRepository;

	public GetApplicablePriceService(PriceRepository priceRepository) {
		this.priceRepository = priceRepository;
	}

	@Override
	public Price getApplicablePrice(LocalDateTime applicationDate, Long brandId, Long productId) {
		return priceRepository.findApplicablePrice(applicationDate, brandId, productId)
				.orElseThrow(() -> new PriceNotFoundException(brandId, productId, applicationDate));
	}
}
