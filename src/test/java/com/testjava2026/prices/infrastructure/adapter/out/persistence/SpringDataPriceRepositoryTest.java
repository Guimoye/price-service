package com.testjava2026.prices.infrastructure.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SpringDataPriceRepositoryTest {

	private static final Long BRAND_ID = 1L;
	private static final Long PRODUCT_ID = 35455L;

	@Autowired
	private SpringDataPriceRepository repository;

	@Test
	void ganaLaMayorPrioridadCuandoSeSolapanTarifas() {
		Optional<PriceJpaEntity> resultado = repository.findApplicable(
				BRAND_ID, PRODUCT_ID, LocalDateTime.of(2020, 6, 14, 16, 0));

		assertThat(resultado).isPresent();
		assertThat(resultado.get().getPriceList()).isEqualTo(2);
		assertThat(resultado.get().getPriority()).isEqualTo(1);
		assertThat(resultado.get().getPrice()).isEqualByComparingTo("25.45");
	}

	@Test
	void devuelveTarifaBaseCuandoNoHaySolapamiento() {
		Optional<PriceJpaEntity> resultado = repository.findApplicable(
				BRAND_ID, PRODUCT_ID, LocalDateTime.of(2020, 6, 14, 10, 0));

		assertThat(resultado).isPresent();
		assertThat(resultado.get().getPriceList()).isEqualTo(1);
		assertThat(resultado.get().getPriority()).isZero();
	}

	@Test
	void devuelveVacioCuandoLaFechaNoTieneTarifa() {
		Optional<PriceJpaEntity> resultado = repository.findApplicable(
				BRAND_ID, PRODUCT_ID, LocalDateTime.of(2019, 1, 1, 0, 0));

		assertThat(resultado).isEmpty();
	}

	@Test
	void devuelveVacioCuandoElProductoNoExiste() {
		Optional<PriceJpaEntity> resultado = repository.findApplicable(
				BRAND_ID, 99999L, LocalDateTime.of(2020, 6, 14, 10, 0));

		assertThat(resultado).isEmpty();
	}

	@Test
	void devuelveVacioCuandoLaCadenaNoExiste() {
		Optional<PriceJpaEntity> resultado = repository.findApplicable(
				2L, PRODUCT_ID, LocalDateTime.of(2020, 6, 14, 10, 0));

		assertThat(resultado).isEmpty();
	}
}
