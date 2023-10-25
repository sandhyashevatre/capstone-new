package com.capstone.telecom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "msisdn")
@Data
public class MSISDN {

    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[1-9][0-9]{9}$", message = "Msisdn Should be 10 digits")
    private String msisdnNumber;

    public MSISDN(String phoneNumber) {
        this.msisdnNumber=phoneNumber;
    }

    public MSISDN() {
    }

}
