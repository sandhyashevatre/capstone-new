package com.capstone.telecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.telecom.entity.ICCID;

public interface ICCIDRepository extends JpaRepository<ICCID, Long> {

    ICCID findByIccidNumber(String string);

    Optional<ICCID> findByNetworkProvider(String anyString);
   
}
