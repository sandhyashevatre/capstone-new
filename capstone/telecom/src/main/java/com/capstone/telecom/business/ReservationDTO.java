package com.capstone.telecom.business;

import lombok.Data;

@Data
public class ReservationDTO {
    private String customerName;

    private String phoneNumber;

    private String provider;

    private String location;
}
