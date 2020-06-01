package com.beroutess.beroutess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.beroutess.beroutess.domain.Valuation;

@Service
public interface ValuationRepository extends JpaRepository<Valuation, Long> {

}
