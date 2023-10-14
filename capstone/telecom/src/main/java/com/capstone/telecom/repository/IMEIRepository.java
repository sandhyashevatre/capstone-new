package com.capstone.telecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.telecom.entity.IMEI;

public interface IMEIRepository extends JpaRepository<IMEI, Long> {
    // You can define custom query methods here if needed
}