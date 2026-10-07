package com.testjava2026.prices.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PriceTest {

	private static final LocalDateTime INICIO = LocalDateTime.of(2020, 6, 14, 0, 0);
	private static final LocalDateTime FIN = LocalDateTime.of(2020, 12, 31, 23, 59, 59);

	@Test
	void creaPrecioValido() {
		Price price = new Price(1L, 35455L, 1, INICIO, FIN, new BigDecimal("35.50"), "EUR");

		assertThat(price.brandId()).isEqualTo(1L);
		assertThat(price.productId()).isEqualTo(35455L);
		assertThat(price.priceList()).isEqualTo(1);
		assertThat(price.startDate()).isEqualTo(INICIO);
		assertThat(price.endDate()).isEqualTo(FIN);
		assertThat(price.price()).isEqualByComparingTo("35.50");
		assertThat(price.currency()).isEqualTo("EUR");
	}

	@Test
	void permiteFechaFinIgualAFechaInicio() {
		Price price = new Price(1L, 35455L, 1, INICIO, INICIO, BigDecimal.ZERO, "EUR");

		assertThat(price.startDate()).isEqualTo(price.endDate());
	}

	@Test
	void rechazaCadenaNula() {
		assertThatThrownBy(() -> new Price(null, 35455L, 1, INICIO, FIN, BigDecimal.TEN, "EUR"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("El identificador de la cadena no puede ser nulo");
	}

	@Test
	void rechazaProductoNulo() {
		assertThatThrownBy(() -> new Price(1L, null, 1, INICIO, FIN, BigDecimal.TEN, "EUR"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("El identificador del producto no puede ser nulo");
	}

	@Test
	void rechazaTarifaNula() {
		assertThatThrownBy(() -> new Price(1L, 35455L, null, INICIO, FIN, BigDecimal.TEN, "EUR"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("La tarifa no puede ser nula");
	}

	@Test
	void rechazaFechaInicioNula() {
		assertThatThrownBy(() -> new Price(1L, 35455L, 1, null, FIN, BigDecimal.TEN, "EUR"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("La fecha de inicio no puede ser nula");
	}

	@Test
	void rechazaFechaFinNula() {
		assertThatThrownBy(() -> new Price(1L, 35455L, 1, INICIO, null, BigDecimal.TEN, "EUR"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("La fecha de fin no puede ser nula");
	}

	@Test
	void rechazaPrecioNulo() {
		assertThatThrownBy(() -> new Price(1L, 35455L, 1, INICIO, FIN, null, "EUR"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("El precio no puede ser nulo");
	}

	@Test
	void rechazaFechasInvertidas() {
		assertThatThrownBy(() -> new Price(1L, 35455L, 1, FIN, INICIO, BigDecimal.TEN, "EUR"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("La fecha de fin no puede ser anterior a la fecha de inicio");
	}

	@Test
	void rechazaPrecioNegativo() {
		assertThatThrownBy(() -> new Price(1L, 35455L, 1, INICIO, FIN, new BigDecimal("-0.01"), "EUR"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("El precio no puede ser negativo");
	}

	@Test
	void rechazaMonedaNula() {
		assertThatThrownBy(() -> new Price(1L, 35455L, 1, INICIO, FIN, BigDecimal.TEN, null))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("La moneda no puede estar vacía");
	}

	@Test
	void rechazaMonedaEnBlanco() {
		assertThatThrownBy(() -> new Price(1L, 35455L, 1, INICIO, FIN, BigDecimal.TEN, "  "))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("La moneda no puede estar vacía");
	}
}
