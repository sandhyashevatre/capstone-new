package com.capstone.telecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.telecom.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    // You can define custom query methods here if needed
}
