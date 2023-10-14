package com.capstone.telecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.telecom.entity.ICCID;

public interface ICCIDRepository extends JpaRepository<ICCID, Long> {
    // You can define custom query methods here if needed
}
