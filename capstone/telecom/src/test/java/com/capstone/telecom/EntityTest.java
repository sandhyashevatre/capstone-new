package com.capstone.telecom;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.capstone.telecom.entity.ICCID;
import com.capstone.telecom.entity.IMEI;
import com.capstone.telecom.entity.MSISDN;
import com.capstone.telecom.repository.ICCIDRepository;
import com.capstone.telecom.repository.IMEIRepository;
import com.capstone.telecom.repository.MSISDNRepository;

import jakarta.validation.ConstraintViolationException;

@DataJpaTest
public class EntityTest {

    @Autowired
    private ICCIDRepository iccidRepository;

    @Autowired
    private MSISDNRepository msisdnRepository;

    @Autowired
    private IMEIRepository imeiRepository;

    //ICCID tests

    @Test
    public void testSaveICCID() {
        ICCID iccid = new ICCID();
        iccid.setIccidNumber("12345678901234567890");

        ICCID savedICCID = iccidRepository.save(iccid);
        assertNotNull(savedICCID.getId());
    }

    @Test
    public void testFindICCIDByIccidNumber() {
        ICCID iccid = new ICCID();
        iccid.setIccidNumber("12345678901234567890");//20 digits

        iccidRepository.save(iccid);

        ICCID foundICCID = iccidRepository.findByIccidNumber("12345678901234567890");
        assertNotNull(foundICCID);
    }

    @Test
    public void testIccidNumberIsNotBlank() {
        ICCID iccid = new ICCID();

        assertThrows(ConstraintViolationException.class, () -> iccidRepository.save(iccid));
    }

    //MSIDN test 
      @Test
    public void testSaveMSISDN() {
        MSISDN msisdn = new MSISDN();
        msisdn.setMsisdnNumber("1234567890");

        MSISDN savedMSISDN = msisdnRepository.save(msisdn);
        assertNotNull(savedMSISDN.getId());
    }

    @Test
    public void testFindMSISDNByMsisdnNumber() {
        MSISDN msisdn = new MSISDN();
        msisdn.setMsisdnNumber("1234567890");

        msisdnRepository.save(msisdn);

        MSISDN foundMSISDN = msisdnRepository.findBymsisdnNumber("1234567890").orElse(null);
        assertNotNull(foundMSISDN);
    }

    @Test
    public void testMsisdnNumberIsNotBlank() {
        MSISDN msisdn = new MSISDN();

        assertThrows(ConstraintViolationException.class, () -> msisdnRepository.save(msisdn));
    }

      
    //IMEI tests
    @Test
    public void testSaveIMEI() {
        IMEI imei = new IMEI();
        imei.setImeiNumber("123456789012345");

        IMEI savedIMEI = imeiRepository.save(imei);
        assertNotNull(savedIMEI.getId());
    }

    @Test
    public void testFindIMEIByImeiNumber() {
        IMEI imei = new IMEI();
        imei.setImeiNumber("123456789012345");

        imeiRepository.save(imei);

        IMEI foundIMEI = imeiRepository.findByImeiNumber("123456789012345").orElse(null);
        assertNotNull(foundIMEI);
    }

    @Test
    public void testImeiNumberIsNotBlank() {
        IMEI imei = new IMEI();

        assertThrows(ConstraintViolationException.class, () -> imeiRepository.save(imei));
    }




}