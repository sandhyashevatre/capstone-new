package com.capstone.telecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.telecom.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{

    Optional<Customer> findByName(String customerName);
    
}
