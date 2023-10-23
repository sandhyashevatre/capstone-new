package com.capstone.telecom.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;

import lombok.Data;

@Entity

@Data

public class Reservation {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String phoneNumber;

    private String provider;

    private String connectionType;

    @ManyToOne

    private Customer customer;

    private LocalDateTime reservationDateTime;

}
