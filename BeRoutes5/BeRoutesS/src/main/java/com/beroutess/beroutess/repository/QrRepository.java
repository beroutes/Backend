package com.beroutess.beroutess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


import com.beroutess.beroutess.domain.Qr;

@Service
public interface QrRepository extends JpaRepository<Qr, Long> {

}
