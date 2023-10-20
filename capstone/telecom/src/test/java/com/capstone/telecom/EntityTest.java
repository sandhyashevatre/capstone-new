package com.capstone.telecom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.capstone.telecom.dto.InsertSimDTO;
import com.capstone.telecom.dto.ReservationDTO;
import com.capstone.telecom.dto.SimDTO;
import com.capstone.telecom.entity.ICCID;
import com.capstone.telecom.entity.IMEI;
import com.capstone.telecom.entity.MSISDN;
import com.capstone.telecom.repository.ICCIDRepository;
import com.capstone.telecom.repository.IMEIRepository;
import com.capstone.telecom.repository.MSISDNRepository;

@DataJpaTest
public class EntityTest {

    @Autowired
    private ICCIDRepository iccidRepository;

    @Autowired
    private MSISDNRepository msisdnRepository;

    @Autowired
    private IMEIRepository imeiRepository;

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
    public void testFindIMEIByImeiNumber() {
        IMEI imei = new IMEI();
        imei.setImeiNumber("123456789012345");

        imeiRepository.save(imei);

        IMEI foundIMEI = imeiRepository.findByImeiNumber("123456789012345").orElse(null);
        assertNotNull(foundIMEI);
    }

        @Test
        void testReservationDTO() {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setCustomerName("John Doe");
        reservationDTO.setProvider("Telecom Co.");
        reservationDTO.setReservingNumber("1234567890");
        reservationDTO.setLocation("New York");
        reservationDTO.setConnectionType("4G");

        assertEquals("John Doe", reservationDTO.getCustomerName());
        assertEquals("Telecom Co.", reservationDTO.getProvider());
        assertEquals("1234567890", reservationDTO.getReservingNumber());
        assertEquals("New York", reservationDTO.getLocation());
        assertEquals("4G", reservationDTO.getConnectionType());
    }

     @Test
    void testInsertSimDTO() {
        InsertSimDTO insertSimDTO = new InsertSimDTO();
        insertSimDTO.setImei("1234567890");
        insertSimDTO.setMsisdn("9876543210");

        assertEquals("1234567890", insertSimDTO.getImei());
        assertEquals("9876543210", insertSimDTO.getMsisdn());
    }

    @Test
    void testSimDTO() {
        SimDTO simDTO = new SimDTO();
        simDTO.setId(1);
        simDTO.setMsisdn("9876543210");
        simDTO.setIccid("ICCID123");
        simDTO.setImei("1234567890");
        simDTO.setReservationDateTime("2023-10-20T10:00:00");
        simDTO.setActivated(true);
        simDTO.setCustomerName("John Doe");

        assertEquals(1, simDTO.getId());
        assertEquals("9876543210", simDTO.getMsisdn());
        assertEquals("ICCID123", simDTO.getIccid());
        assertEquals("1234567890", simDTO.getImei());
        assertEquals("2023-10-20T10:00:00", simDTO.getReservationDateTime());
        assertEquals(true, simDTO.isActivated());
        assertEquals("John Doe", simDTO.getCustomerName());
    }   


}