package com.testjava2026.prices.infrastructure.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES", indexes = @Index(
		name = "idx_prices_lookup",
		columnList = "BRAND_ID, PRODUCT_ID, START_DATE, END_DATE, PRIORITY"))
public class PriceJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "BRAND_ID", nullable = false)
	private Long brandId;

	@Column(name = "PRODUCT_ID", nullable = false)
	private Long productId;

	@Column(name = "PRICE_LIST", nullable = false)
	private Integer priceList;

	@Column(name = "START_DATE", nullable = false)
	private LocalDateTime startDate;

	@Column(name = "END_DATE", nullable = false)
	private LocalDateTime endDate;

	@Column(name = "PRIORITY", nullable = false)
	private Integer priority;

	@Column(name = "PRICE", nullable = false, precision = 10, scale = 2)
	private BigDecimal price;

	@Column(name = "CURR", nullable = false, length = 3)
	private String currency;

	protected PriceJpaEntity() {
	}

	public Long getId() {
		return id;
	}

	public Long getBrandId() {
		return brandId;
	}

	public Long getProductId() {
		return productId;
	}

	public Integer getPriceList() {
		return priceList;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public Integer getPriority() {
		return priority;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getCurrency() {
		return currency;
	}
}
