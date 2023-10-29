package com.capstone.telecom;

import com.capstone.telecom.entity.ICCID;
import com.capstone.telecom.entity.MSISDN;
import com.capstone.telecom.entity.IMEI;
import com.capstone.telecom.entity.User;
import com.capstone.telecom.repository.ICCIDRepository;
import com.capstone.telecom.repository.MSISDNRepository;
import com.capstone.telecom.repository.IMEIRepository;
import com.capstone.telecom.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RepositoryTests {

    @Autowired
    private ICCIDRepository iccidRepository;

    @Autowired
    private MSISDNRepository msisdnRepository;

    @Autowired
    private IMEIRepository imeiRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testICCIDRepository() {
        ICCID iccid = new ICCID();
        iccid.setIccidNumber("12345678901234567890");

        ICCID savedICCID = iccidRepository.save(iccid);
        assertNotNull(savedICCID);
    }

    @Test
    public void testMSISDNRepository() {
        MSISDN msisdn = new MSISDN();
        msisdn.setMsisdnNumber("1234567890");

        MSISDN savedMSISDN = msisdnRepository.save(msisdn);
        assertNotNull(savedMSISDN);
    }

    @Test
    public void testIMEIRepository() {
        IMEI imei = new IMEI();
        imei.setImeiNumber("123456789012345");

        IMEI savedIMEI = imeiRepository.save(imei);
        assertNotNull(savedIMEI);
    }

    @Test
    public void testUserRepository() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole("ROLE_USER");

        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
    }
}