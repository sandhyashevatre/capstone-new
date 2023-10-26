package com.capstone.telecom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.capstone.telecom.controller.WinmController;
import com.capstone.telecom.dto.InsertSimDTO;
import com.capstone.telecom.dto.ReservationDTO;
import com.capstone.telecom.dto.SimDTO;
import com.capstone.telecom.entity.Customer;
import com.capstone.telecom.entity.ICCID;
import com.capstone.telecom.entity.IMEI;
import com.capstone.telecom.entity.MSISDN;
import com.capstone.telecom.entity.Registration;
import com.capstone.telecom.repository.CustomerRepository;
import com.capstone.telecom.repository.ICCIDRepository;
import com.capstone.telecom.repository.IMEIRepository;
import com.capstone.telecom.repository.MSISDNRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TelecomApplicationTests {

    @Mock
    private ICCIDRepository iccidRepository;

    @Mock
    private MSISDNRepository msisdnRepository;

    @Mock
    private IMEIRepository imeiRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Autowired
    private WinmController winmController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach

    void setUp() {

    }

    @Test

    @WithMockUser

    void testCreateICCID() throws JsonProcessingException, Exception {

        ICCID iccid = new ICCID();

        iccid.setIccidNumber("12345678901234567890");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/iccid")

                .contentType("application/json") 

                .content(objectMapper.writeValueAsString(iccid)))

                .andReturn();

        int status = result.getResponse().getStatus();

        assertEquals(HttpStatus.OK.value(), status);

    }

    @Test
    @WithMockUser
    void testReservationIntegration() throws Exception {

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setCustomerName("John Doe");
        reservationDTO.setProvider("AIRTEL");
        reservationDTO.setReservingNumber("1234567890");
        reservationDTO.setLocation("New York");
        reservationDTO.setConnectionType("prepaid");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/reservation")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(reservationDTO)))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    @WithMockUser
    void testInsertSimIntegration() throws Exception {
   
        InsertSimDTO insertSimDTO = new InsertSimDTO();
        insertSimDTO.setImei("1234567890");
        insertSimDTO.setMsisdn("9876543210");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/insertSim")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(insertSimDTO)))
                .andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    @WithMockUser
    void testChangeProviderIntegration() throws Exception {

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setCustomerName("John Doe");
        reservationDTO.setProvider("New Provider");
        reservationDTO.setReservingNumber("1234567890");
        reservationDTO.setLocation("New York");
        reservationDTO.setConnectionType("4G");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/changeProvider")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(reservationDTO)))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    @WithMockUser
    void testGetAllPrepaidSimsIntegration() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/allprepaidsims"))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    @WithMockUser
    void testGetAllPostpaidSimsIntegration() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/allpostpaidsims"))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    void testActivate() {
        Boolean result = winmController.activate();
        assertEquals(true, result);
    }

    @Test
    void testImeiAlreadyExist() {

        boolean result = winmController.imeiAlreadyExist("1234567890");
        assertEquals(false, result);
    }

    @Test
    public void testSetSIMInPhoneWithIMEI_Success() {
        
        String imeiNumber = "123456789012345";
        String phoneNumber = "9876543210";
        Customer customer = new Customer();
        Registration sim = new Registration();
        sim.setMsisdn(new MSISDN(phoneNumber));
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        
        Boolean result = winmController.setSIMInPhoneWithIMEI(imeiNumber, phoneNumber);

    
        assertFalse(result);
    }

    @Test
    public void testSetSIMInPhoneWithIMEI_ImeiAlreadyExists() {
    
        String imeiNumber = "123456789012345";
        String phoneNumber = "9876543210";


    
        Boolean result = winmController.setSIMInPhoneWithIMEI(imeiNumber, phoneNumber);

    
        assertFalse(result);
    }

    @Test
    void testConvertToDTO() {
    
        Registration registration1 = new Registration();
        registration1.setId(1);
        ICCID iccid1 = new ICCID();
        iccid1.setIccidNumber("1234567890");
        registration1.setIccid(iccid1);
        MSISDN msisdn1 = new MSISDN();
        msisdn1.setMsisdnNumber("9876543210");
        registration1.setMsisdn(msisdn1);
        IMEI imei1 = new IMEI();
        imei1.setImeiNumber("111111");
        registration1.setImei(imei1);
        registration1.setActivated(true);
        registration1.setRegistrationtTime(LocalDateTime.now());

        Registration registration2 = new Registration();
        registration2.setId(2);
        ICCID iccid2 = new ICCID();
        iccid2.setIccidNumber("9999999999");
        registration2.setIccid(iccid2);
        MSISDN msisdn2 = new MSISDN();
        msisdn2.setMsisdnNumber("5555555555");
        registration2.setMsisdn(msisdn2);
        registration2.setActivated(false);
        registration2.setRegistrationtTime(LocalDateTime.now());

        List<Registration> registrations = List.of(registration1, registration2);

        List<SimDTO> simDTOs = winmController.convertToDTO(registrations);

        assertEquals(2, simDTOs.size());
        assertEquals(1, simDTOs.get(0).getId());
        assertEquals("1234567890", simDTOs.get(0).getIccid());
        assertEquals("9876543210", simDTOs.get(0).getMsisdn());
        assertEquals("111111", simDTOs.get(0).getImei());
        assertEquals(true, simDTOs.get(0).isActivated());

        assertEquals(2, simDTOs.get(1).getId());
        assertEquals("9999999999", simDTOs.get(1).getIccid());
        assertEquals("5555555555", simDTOs.get(1).getMsisdn());
        assertEquals("", simDTOs.get(1).getImei());
        assertEquals(false, simDTOs.get(1).isActivated());
    }
}
