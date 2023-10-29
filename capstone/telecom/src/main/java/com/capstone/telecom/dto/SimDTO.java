package com.capstone.telecom.dto;

import lombok.Data;

@Data
public class SimDTO {

    private int id;

    private String msisdn;

    private String iccid;

    private String imei;

    private String reservationDateTime;

    private boolean isActivated;

    private String customerName;

    private String connectionType;

    private String networkProvider;

}
