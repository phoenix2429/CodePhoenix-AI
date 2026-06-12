package com.codephoenix.repository;

import com.codephoenix.entity.TechnicalDebt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicalDebtRepository extends JpaRepository<TechnicalDebt, Long> {
}