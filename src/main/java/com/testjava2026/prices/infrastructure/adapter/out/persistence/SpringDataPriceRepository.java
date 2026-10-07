package com.testjava2026.prices.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SpringDataPriceRepository extends JpaRepository<PriceJpaEntity, Long> {

	@Query("""
			select p from PriceJpaEntity p
			where p.brandId = :brandId
			and p.productId = :productId
			and p.startDate <= :applicationDate
			and p.endDate >= :applicationDate
			order by p.priority desc
			limit 1
			""")
	Optional<PriceJpaEntity> findApplicable(
			@Param("brandId") Long brandId,
			@Param("productId") Long productId,
			@Param("applicationDate") LocalDateTime applicationDate);
}
