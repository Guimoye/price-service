package com.testjava2026.prices.application.service;

import com.testjava2026.prices.application.exception.PriceNotFoundException;
import com.testjava2026.prices.application.port.out.PriceRepository;
import com.testjava2026.prices.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetApplicablePriceServiceTest {

	@Mock
	private PriceRepository priceRepository;

	@InjectMocks
	private GetApplicablePriceService getApplicablePriceService;

	@Test
	void devuelvePrecioCuandoExisteTarifa() {
		LocalDateTime fecha = LocalDateTime.of(2020, 6, 14, 10, 0);
		Price esperado = new Price(1L, 35455L, 1, LocalDateTime.of(2020, 6, 14, 0, 0),
				LocalDateTime.of(2020, 12, 31, 23, 59, 59), new BigDecimal("35.50"), "EUR");
		when(priceRepository.findApplicablePrice(fecha, 1L, 35455L)).thenReturn(Optional.of(esperado));

		Price resultado = getApplicablePriceService.getApplicablePrice(fecha, 1L, 35455L);

		assertThat(resultado).isEqualTo(esperado);
		verify(priceRepository).findApplicablePrice(fecha, 1L, 35455L);
	}

	@Test
	void lanzaExcepcionCuandoNoHayTarifa() {
		LocalDateTime fecha = LocalDateTime.of(2020, 1, 1, 0, 0);
		when(priceRepository.findApplicablePrice(fecha, 1L, 35455L)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> getApplicablePriceService.getApplicablePrice(fecha, 1L, 35455L))
				.isInstanceOf(PriceNotFoundException.class);
	}
}
