package com.capstone.telecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.telecom.entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration,Integer>{
    
}
