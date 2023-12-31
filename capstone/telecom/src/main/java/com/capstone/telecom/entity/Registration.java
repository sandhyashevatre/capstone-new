package com.capstone.telecom.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private ICCID iccid;

    @OneToOne
    private MSISDN msisdn;

    @OneToOne
    private IMEI imei;

    private boolean isActivated;

    private LocalDateTime registrationtTime;

    private String connectionType;
}
