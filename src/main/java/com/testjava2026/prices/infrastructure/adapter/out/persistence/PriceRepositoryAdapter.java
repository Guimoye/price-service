package com.testjava2026.prices.infrastructure.adapter.out.persistence;

import com.testjava2026.prices.application.port.out.PriceRepository;
import com.testjava2026.prices.domain.model.Price;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class PriceRepositoryAdapter implements PriceRepository {

	private final SpringDataPriceRepository springDataPriceRepository;

	public PriceRepositoryAdapter(SpringDataPriceRepository springDataPriceRepository) {
		this.springDataPriceRepository = springDataPriceRepository;
	}

	@Override
	public Optional<Price> findApplicablePrice(LocalDateTime applicationDate, Long brandId, Long productId) {
		return springDataPriceRepository
				.findApplicable(brandId, productId, applicationDate)
				.map(this::toDomain);
	}

	private Price toDomain(PriceJpaEntity entity) {
		return new Price(
				entity.getBrandId(),
				entity.getProductId(),
				entity.getPriceList(),
				entity.getStartDate(),
				entity.getEndDate(),
				entity.getPrice(),
				entity.getCurrency());
	}
}
