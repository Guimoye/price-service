package com.testjava2026.prices.application.service;

import com.testjava2026.prices.application.exception.PriceNotFoundException;
import com.testjava2026.prices.application.port.in.GetApplicablePriceUseCase;
import com.testjava2026.prices.application.port.out.PriceRepository;
import com.testjava2026.prices.domain.model.Price;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
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
