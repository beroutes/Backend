package com.beroutess.beroutess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beroutess.beroutess.domain.Country;



@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
	 

}
