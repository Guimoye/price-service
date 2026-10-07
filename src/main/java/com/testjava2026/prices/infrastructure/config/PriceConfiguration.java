package com.testjava2026.prices.infrastructure.config;

import com.testjava2026.prices.application.port.in.GetApplicablePriceUseCase;
import com.testjava2026.prices.application.port.out.PriceRepository;
import com.testjava2026.prices.application.service.GetApplicablePriceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PriceConfiguration {

	@Bean
	public GetApplicablePriceUseCase getApplicablePriceUseCase(PriceRepository priceRepository) {
		return new GetApplicablePriceService(priceRepository);
	}
}
