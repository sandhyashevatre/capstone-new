package com.capstone.telecom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
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

    // @Test
    // void testSetSIMInPhoneWithIMEI() {
    // // Create test data in the repositories
    // when(customerRepository.findAll()).thenReturn(new ArrayList<>());
    // when(imeiRepository.save(any(IMEI.class))).thenReturn(new IMEI());

    // boolean result = winmController.setSIMInPhoneWithIMEI("123456789012345",
    // "9876543210");

    // // Assert that the result is true (or add specific assertions based on your
    // business logic)
    // assertEquals(true, result);
    // }

    // // Test getAllPrepaidSims method
    // @Test
    // void testGetAllPrepaidSims() {
    // // Create test data for customers and registrations
    // Customer customer = new Customer();
    // Registration prepaidSim1 = new Registration();
    // prepaidSim1.setConnectionType("prepaid");
    // Registration postpaidSim = new Registration();
    // postpaidSim.setConnectionType("postpaid");
    // Registration prepaidSim2 = new Registration();
    // prepaidSim2.setConnectionType("prepaid");
    // customer.setRegistrations(List.of(prepaidSim1, postpaidSim, prepaidSim2));

    // when(customerRepository.save(any(Customer.class))).thenReturn(customer);
    // when(customerRepository.findAll()).thenReturn(List.of(customer));

    // List<Registration> prepaidSims = winmController.getAllPrepaidSims();

    // // Assert that the list contains only prepaid sims
    // assertEquals(2, prepaidSims.size());
    // }

    // // Test changeProvider method
    // @Test
    // @Transactional
    // void testChangeProvider() {
    // // Create a test ReservationDTO
    // ReservationDTO reservationDTO = new ReservationDTO();
    // reservationDTO.setCustomerName("TestCustomer");
    // reservationDTO.setReservingNumber("1234567890");
    // reservationDTO.setProvider("Provider1");
    // reservationDTO.setConnectionType("prepaid");

    // // Create test data for customers and registrations
    // Customer customer = new Customer();
    // Registration sim = new Registration();
    // sim.setConnectionType("prepaid");
    // MSISDN msisdn = new MSISDN();
    // msisdn.setMsisdnNumber("1234567890");
    // sim.setMsisdn(msisdn);

    // when(customerRepository.findByName("TestCustomer")).thenReturn(java.util.Optional.of(customer));
    // when(iccidRepository.save(any(ICCID.class))).thenReturn(new ICCID());

    // Boolean status = winmController.changeProvider(reservationDTO);

    // // Assert that the status is true (or add specific assertions based on your
    // business logic)
    // assertEquals(true, status);
    // }

    // // Test changeProviderTo method
    // @Test
    // @Transactional
    // void testChangeProviderTo() {
    // // Create test data for customer and registration
    // Customer customer = new Customer();
    // Registration sim = new Registration();
    // sim.setConnectionType("prepaid");
    // MSISDN msisdn = new MSISDN();
    // msisdn.setMsisdnNumber("1234567890");
    // sim.setMsisdn(msisdn);
    // ICCID iccid = new ICCID();
    // iccid.setIccidNumber("9876543210");
    // iccid.setNetworkProvider("Provider1");
    // sim.setIccid(iccid);

    // when(customerRepository.save(any(Customer.class))).thenReturn(customer);
    // when(iccidRepository.save(any(ICCID.class))).thenReturn(new ICCID());

    // Boolean status = winmController.changeProviderTo("1234567890", "Provider2",
    // customer, "prepaid");

    // // Assert that the status is true (or add specific assertions based on your
    // business logic)
    // assertEquals(true, status);
    // }

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

    // Test getOrCreateCustomer method
    // @Test
    // @Transactional
    // void testGetOrCreateCustomer() {
    // // Create a test customer name
    // String customerName = "TestCustomer";

    // // Create a test customer
    // Customer customer = new Customer();
    // customer.setName(customerName);

    // when(customerRepository.findByName(customerName)).thenReturn(java.util.Optional.of(customer));
    // when(customerRepository.save(any(Customer.class))).thenReturn(customer);

    // Customer resultCustomer = winmController.getOrCreateCustomer(customerName);

    // // Assert that the result is the same as the test customer
    // assertEquals(customer, resultCustomer);
    // }
}
