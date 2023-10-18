package com.capstone.telecom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.capstone.telecom.business.ReservationDTO;
import com.capstone.telecom.controller.WinmController;
import com.capstone.telecom.entity.Customer;
import com.capstone.telecom.entity.ICCID;
import com.capstone.telecom.entity.MSISDN;
import com.capstone.telecom.entity.Registration;
import com.capstone.telecom.repository.CustomerRepository;
import com.capstone.telecom.repository.ICCIDRepository;
import com.capstone.telecom.repository.IMEIRepository;
import com.capstone.telecom.repository.MSISDNRepository;
import com.capstone.telecom.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class TelecomApplicationTests {

    @Autowired
    private ICCIDRepository iccidRepository;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WinmController winmController;
    
    @Autowired
    private MSISDNRepository msisdnRepository;
    
    @Autowired
    private IMEIRepository imeiRepository;
    
    @Autowired
    private UserRepository userRepository;
   
    
    @Autowired
    private CustomerRepository customerRepository;

    @MockBean
    private ObjectMapper objectMapper;

    @Test
	void contextLoads() {
	}

    @BeforeEach
    void setUp() {
       
        when(iccidRepository.findAll()).thenReturn(new ArrayList<>());
        when(msisdnRepository.findAll()).thenReturn(new ArrayList<>());
        when(imeiRepository.findAll()).thenReturn(new ArrayList<>());
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        when(customerRepository.findAll()).thenReturn(new ArrayList<>());
    }

    // @Test
    // @WithMockUser()
    // void testCreateICCID() throws Exception {
    //     ICCID iccid = new ICCID();
    //     iccid.setIccidNumber("12345678901234567890");

    //     MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/iccid")
    //             .content("application/json")
    //             .content(objectMapper.writeValueAsString(iccid))).andReturn();

    //     int status = result.getResponse().getStatus();
    //     assertEquals(HttpStatus.OK.value(),status);
    // }

    @Test
    @WithMockUser()
    void testGetAllICCID() throws Exception {
        ICCID iccid1 = new ICCID();
        iccid1.setIccidNumber("12345678901234567890");
        ICCID iccid2 = new ICCID();
        iccid2.setIccidNumber("98765432109876543210");

        iccidRepository.save(iccid1);
        iccidRepository.save(iccid2);
        when(iccidRepository.findByIccidNumber("12345678901234567890")).thenReturn(iccid1);
        when(iccidRepository.findByIccidNumber("98765432109876543210")).thenReturn(iccid2);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/iccid"))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(),status);

    }

    // @Test
    // void testReserveNumber() {
    //     ReservationDTO reservationDTO = new ReservationDTO();
    //     reservationDTO.setCustomerName("John Doe");
    //     reservationDTO.setPhoneNumber("1234567890");
    //     reservationDTO.setProvider("airtel");

    //     when(customerRepository.findByName("John Doe")).thenReturn(Optional.of(new Customer()));
    //     when(msisdnRepository.findBymsisdnNumber("1234567890")).thenReturn(Optional.of(new MSISDN()));

    //     ResponseEntity<Boolean> response = winmController.reserveNumber(reservationDTO);
    //     assertEquals(true, response.getBody());
    // }

    // @Test
    // void testActivate() {
    //     Registration registration1 = new Registration();
    //     registration1.setRegistrationtTime(LocalDateTime.now().minusDays(2));
    //     Registration registration2 = new Registration();
    //     registration2.setRegistrationtTime(LocalDateTime.now().minusDays(1));
    //     List<Registration> registrations = new ArrayList<>();
    //     registrations.add(registration1);
    //     registrations.add(registration2);
    //     Customer customer = new Customer();
    //     customer.setRegistrations(registrations);
    //     when(customerRepository.findAll()).thenReturn(List.of(customer));
        
    //     assertTrue(winmController.activate());
    //     assertTrue(registration1.isActivated());
    //     assertTrue(registration2.isActivated());
    //     verify(iccidRepository, times(2)).save(any());
    //     verify(msisdnRepository, times(2)).save(any());
    // }
}