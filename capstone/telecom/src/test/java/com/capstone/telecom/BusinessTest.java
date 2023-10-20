package com.capstone.telecom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.capstone.telecom.business.LoggedInUser;
import com.capstone.telecom.business.LoginBody;
import com.capstone.telecom.business.RandomICCID;
import com.capstone.telecom.dto.ReservationDTO;
import com.capstone.telecom.entity.User;

public class BusinessTest {

    //random ICCID
    @Test
    public void testGenerateValidICCID() {
        String iccid = RandomICCID.generate("airtel");
        assertNotNull(iccid);
        assertTrue(iccid.matches("89[0-9]{17}X"));
    }

    @Test
    public void testGenerateInvalidProvider() {
        assertThrows(IllegalArgumentException.class, () -> RandomICCID.generate("invalidprovider"));
    }

    //loggedinuser
    @Test
    public void testGetSetLoggedInUser() {
        LoggedInUser loggedInUser = new LoggedInUser();
        User user = new User();
        loggedInUser.setLoggedInUser(user);

        assertEquals(user, loggedInUser.getLoggedInUser());
    }

    //login body

    @Test
    public void testGetSetUsername() {
        LoginBody loginBody = new LoginBody();
        loginBody.setUsername("testuser");

        assertEquals("testuser", loginBody.getUsername());
    }

    @Test
    public void testGetSetPassword() {
        LoginBody loginBody = new LoginBody();
        loginBody.setPassword("password");

        assertEquals("password", loginBody.getPassword());
    }

    // reservation DTO
    @Test
    public void testGetSetCustomerName() {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setCustomerName("John Doe");

        assertEquals("John Doe", reservationDTO.getCustomerName());
    }

    @Test
    public void testGetSetPhoneNumber() {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setReservingNumber("1234567890");

        assertEquals("1234567890", reservationDTO.getReservingNumber());
    }

    @Test
    public void testGetSetProvider() {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setProvider("airtel");

        assertEquals("airtel", reservationDTO.getProvider());
    }

    @Test
    public void testGetSetLocation() {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setLocation("New York");

        assertEquals("New York", reservationDTO.getLocation());
    }
}
