package com.capstone.telecom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

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
import com.capstone.telecom.entity.Reservation;
import com.capstone.telecom.entity.User;
import com.capstone.telecom.repository.CustomerRepository;
import com.capstone.telecom.repository.ICCIDRepository;
import com.capstone.telecom.repository.IMEIRepository;
import com.capstone.telecom.repository.MSISDNRepository;
import com.capstone.telecom.repository.ReservationRepository;
import com.capstone.telecom.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.Mockito.*;

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

    @Mock
    private UserRepository userRepository;

    @Autowired
    private WinmController winmController;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ReservationRepository reservationRepository;

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
    void testSetSIMInPhoneWithIMEI_Success() {

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
    void testSetSIMInPhoneWithIMEI_ImeiAlreadyExists() {

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

    @Test
    @WithMockUser
    void testGetAllICCID() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/iccid"))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    @WithMockUser
    void testCreateICCIDof() throws Exception {
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
    void testGetAllMSISDN() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/msisdn"))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    @WithMockUser
    void testCreateMSISDN() throws Exception {
        MSISDN msisdn = new MSISDN();
        msisdn.setMsisdnNumber("1234567890");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/msisdn")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(msisdn)))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    @WithMockUser
    void testGetAllIMEI() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/imei"))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    @WithMockUser
    void testCreateIMEI() throws Exception {
        IMEI imei = new IMEI();
        imei.setImeiNumber("123456789012345");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/imei")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(imei)))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    @WithMockUser
    void testGetAllUsers() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/user"))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    @WithMockUser
    void testCreateUser() throws Exception {
        User user = new User();
        user.setUsername("testUser");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
    }

    private Customer createCustomerWithRegistration() {
        Customer customer = new Customer();

        Registration registration = new Registration();
        registration.setMsisdn(new MSISDN("9876543210"));

        customer.setRegistrations(List.of(registration));

        customerRepository.save(customer);

        return customer;
    }

    @Test
    void testGetAllReservationsOf_CustomerNotExists() {

        List<Reservation> reservations = winmController.getAllReservationsOf("NonExistentCustomer");

        assertTrue(reservations.isEmpty());
    }

    @Test
    void testSetSIMInPhoneWithIMEI_InvalidPhoneNumber() {

        Customer customer = createCustomerWithRegistration();
        Registration registration = customer.getRegistrations().get(0);
        String newImei = "1234567890";
        String invalidPhoneNumber = "9876543210";

        boolean result = winmController.setSIMInPhoneWithIMEI(newImei, invalidPhoneNumber);

        assertFalse(result);
        assertNull(registration.getImei());
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void testReserveTheNumber_Success() throws Exception {

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setCustomerName("John Doe");
        reservationDTO.setReservingNumber("1234567890");
    }

    @Test
    void testReserveTheNumber_FailureNumberExists() {

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setCustomerName("John Doe");
        reservationDTO.setReservingNumber("1234567890");

        ReservationRepository reservationRepository = mock(ReservationRepository.class);
        TelecomApplicationTests telecomApplicationTests = new TelecomApplicationTests();

        Reservation existingReservation = new Reservation();
        existingReservation.setPhoneNumber("1234567890");

        boolean result = telecomApplicationTests.reserveTheNumber(reservationDTO);

        verify(reservationRepository, times(0)).save(any(Reservation.class));

        assertFalse(result);
    }

    private boolean reserveTheNumber(ReservationDTO reservationDTO) {
        return false;
    }

    // @Test
    // public void testGetAllInactiveSims() {
    //     CustomerRepository customerRepository = mock(CustomerRepository.class);
    //     TelecomApplicationTests telecomApplicationTests = new TelecomApplicationTests();

    //     Registration inactiveSim1 = new Registration();
    //     inactiveSim1.setActivated(false);

    //     Registration activeSim = new Registration();
    //     activeSim.setActivated(true);

    //     Registration inactiveSim2 = new Registration();
    //     inactiveSim2.setActivated(false);

    //     when(customerRepository.findAll()).thenReturn(List.of(
    //             new Customer(),
    //             new Customer(),
    //             new Customer()));

    //     List<Registration> inactiveSims = telecomApplicationTests.getAllInactiveSims();

    //     assertEquals(2, inactiveSims.size());
    //     assertTrue(inactiveSims.contains(inactiveSim1));
    //     assertTrue(inactiveSims.contains(inactiveSim2));
    //     assertFalse(inactiveSims.contains(activeSim));
    // }

    // @Test
    // public void testGetAllReservedSims() {
    //     ReservationRepository reservationRepository = mock(ReservationRepository.class);
    //     TelecomApplicationTests telecomApplicationTests = new TelecomApplicationTests();

    //     Reservation reservedSim1 = new Reservation();
    //     Reservation reservedSim2 = new Reservation();

    //     when(reservationRepository.findAll()).thenReturn(List.of(reservedSim1, reservedSim2));

    //     List<Reservation> reservedSims = telecomApplicationTests.getAllReservedSims();

    //     assertEquals(2, reservedSims.size());
    //     assertTrue(reservedSims.contains(reservedSim1));
    //     assertTrue(reservedSims.contains(reservedSim2));
    // }

    // // Define methods to retrieve inactive and reserved sims from repositories
    // public List<Registration> getAllInactiveSims() {
    //     // Implement logic to retrieve inactive sims and return them
    //     return null;
    // }

    // public List<Reservation> getAllReservedSims() {
    //     // Implement logic to retrieve reserved sims and return them
    //     return null;
    // }
}