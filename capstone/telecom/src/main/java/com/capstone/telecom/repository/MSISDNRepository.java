package com.capstone.telecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.capstone.telecom.entity.MSISDN;

public interface MSISDNRepository extends JpaRepository<MSISDN, Long> {

    Optional<MSISDN> findBymsisdnNumber(String phoneNumber);
    
}
