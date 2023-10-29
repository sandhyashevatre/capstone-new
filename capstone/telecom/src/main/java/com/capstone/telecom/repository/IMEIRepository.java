package com.capstone.telecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.telecom.entity.IMEI;

public interface IMEIRepository extends JpaRepository<IMEI, Long> {

    Optional<IMEI> findByImeiNumber(String string);

}