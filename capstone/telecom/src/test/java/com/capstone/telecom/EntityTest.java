package com.capstone.telecom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.capstone.telecom.dto.InsertSimDTO;
import com.capstone.telecom.dto.ReservationDTO;
import com.capstone.telecom.dto.SimDTO;
import com.capstone.telecom.dto.TokenDTO;
import com.capstone.telecom.entity.Customer;
import com.capstone.telecom.entity.ICCID;
import com.capstone.telecom.entity.IMEI;
import com.capstone.telecom.entity.MSISDN;
import com.capstone.telecom.entity.Reservation;
import com.capstone.telecom.entity.User;
import com.capstone.telecom.repository.CustomerRepository;
import com.capstone.telecom.repository.ICCIDRepository;
import com.capstone.telecom.repository.IMEIRepository;
import com.capstone.telecom.repository.MSISDNRepository;
import com.capstone.telecom.repository.ReservationRepository;
import com.capstone.telecom.repository.UserRepository;

@DataJpaTest
class EntityTest {

    @Autowired
    private ICCIDRepository iccidRepository;

    @Autowired
    private MSISDNRepository msisdnRepository;

    @Autowired
    private IMEIRepository imeiRepository;

    @InjectMocks
    private Reservation reservation;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    void testSaveICCID() {
        ICCID iccid = new ICCID();
        iccid.setIccidNumber("12345678901234567890");

        ICCID savedICCID = iccidRepository.save(iccid);
        assertNotNull(savedICCID.getId());
    }

    @Test
    void testFindICCIDByIccidNumber() {
        ICCID iccid = new ICCID();
        iccid.setIccidNumber("12345678901234567890");// 20 digits

        iccidRepository.save(iccid);

        ICCID foundICCID = iccidRepository.findByIccidNumber("12345678901234567890");
        assertNotNull(foundICCID);
    }

    @Test
    void testSaveMSISDN() {
        MSISDN msisdn = new MSISDN();
        msisdn.setMsisdnNumber("1234567890");

        MSISDN savedMSISDN = msisdnRepository.save(msisdn);
        assertNotNull(savedMSISDN.getId());
    }

    @Test
    void testFindMSISDNByMsisdnNumber() {
        MSISDN msisdn = new MSISDN();
        msisdn.setMsisdnNumber("1234567890");

        msisdnRepository.save(msisdn);

        MSISDN foundMSISDN = msisdnRepository.findBymsisdnNumber("1234567890").orElse(null);
        assertNotNull(foundMSISDN);
    }

    @Test
    void testFindIMEIByImeiNumber() {
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
        simDTO.setConnectionType("prepaid");
        simDTO.setNetworkProvider("airtel");

        assertEquals(1, simDTO.getId());
        assertEquals("9876543210", simDTO.getMsisdn());
        assertEquals("ICCID123", simDTO.getIccid());
        assertEquals("1234567890", simDTO.getImei());
        assertEquals("2023-10-20T10:00:00", simDTO.getReservationDateTime());
        assertEquals(true, simDTO.isActivated());
        assertEquals("John Doe", simDTO.getCustomerName());
        assertEquals("prepaid", simDTO.getConnectionType());
        assertEquals("airtel", simDTO.getNetworkProvider());
    }

    @Test
    void testTokenDTOGettersAndSetters() {
        // Arrange
        TokenDTO tokenDTO = new TokenDTO();

        // Act
        tokenDTO.setToken("myToken");

        // Assert
        assertEquals("myToken", tokenDTO.getToken());
    }

    @Test
    void testTokenDTOEquality() {
        // Arrange
        TokenDTO tokenDTO1 = new TokenDTO();
        tokenDTO1.setToken("token1");

        TokenDTO tokenDTO2 = new TokenDTO();
        tokenDTO2.setToken("token1");

        TokenDTO tokenDTO3 = new TokenDTO();
        tokenDTO3.setToken("token2");

        // Act & Assert
        assertEquals(tokenDTO1, tokenDTO2); // Tokens are the same, so the DTOs should be equal.
        assertNotEquals(tokenDTO1, tokenDTO3); // Tokens are different, so the DTOs should not be equal.
    }

    @Test
    void testTokenDTOHashCode() {
        // Arrange
        TokenDTO tokenDTO1 = new TokenDTO();
        tokenDTO1.setToken("token1");

        TokenDTO tokenDTO2 = new TokenDTO();
        tokenDTO2.setToken("token1");

        TokenDTO tokenDTO3 = new TokenDTO();
        tokenDTO3.setToken("token2");

        // Act & Assert
        assertEquals(tokenDTO1.hashCode(), tokenDTO2.hashCode()); // Tokens are the same, so hash codes should be equal.
        assertNotEquals(tokenDTO1.hashCode(), tokenDTO3.hashCode()); // Tokens are different, so hash codes should not
                                                                     // be equal.
    }

    @Test
    public void testGetId() {
        reservation.setId(1);
        assertEquals(1, reservation.getId());
    }

    @Test
    public void testGetPhoneNumber() {
        reservation.setPhoneNumber("1234567890");
        assertEquals("1234567890", reservation.getPhoneNumber());
    }

    // @Test
    // public void testSaveCustomerEntity() {
    // Customer customer = new Customer();
    // customer.setName("John Doe");

    // try {
    // // Attempt to save the customer entity
    // customer = customerRepository.save(customer);

    // // Ensure that the customer was saved successfully
    // assertNotNull(customer);

    // // Retrieve the saved customer from the database
    // Customer savedCustomer =
    // customerRepository.findByName(customer.getName()).orElse(null);

    // // Perform assertions
    // assertNotNull(savedCustomer);
    // assertEquals(customer.getId(), savedCustomer.getId());
    // assertEquals(customer.getName(), savedCustomer.getName());
    // } catch (Exception e) {
    // e.printStackTrace();
    // fail("An exception occurred while saving the customer entity.");
    // }
    // }

    @Test
    public void testGetCustomer() {
        Customer customer = new Customer();
        reservation.setCustomer(customer);
        assertEquals(customer, reservation.getCustomer());
    }

    @Test
    public void testGetReservationDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        reservation.setReservationDateTime(dateTime);
        assertEquals(dateTime, reservation.getReservationDateTime());
    }

    @Test
    public void testSave() {
        Reservation reservationToSave = new Reservation();
        when(reservationRepository.save(reservationToSave)).thenReturn(reservationToSave);

        Reservation savedReservation = reservationRepository.save(reservationToSave);
        assertEquals(reservationToSave, savedReservation);
    }

    @Test
    public void testFindById() {
        Reservation reservationToFind = new Reservation();
        when(reservationRepository.findById(1)).thenReturn(Optional.of(reservationToFind));

        java.util.Optional<Reservation> foundReservation = reservationRepository.findById(1);
        assertEquals(reservationToFind, foundReservation.orElse(null));
    }

    // @Test
    // public void testSaveAndRetrieveUser() {
    // // Create a User instance with sample data
    // User user = new User();
    // user.setUsername("SampleUsername");
    // user.setPassword("SamplePassword");
    // user.setRole("SampleRole");

    // // Save the User to the repository
    // user = userRepository.save(user);

    // // Retrieve the User by ID
    // User savedUser =
    // userRepository.findByUsername(user.getUsername()).orElse(null);

    // // Perform assertions
    // assertNotNull(savedUser);
    // assertEquals("SampleUsername", savedUser.getUsername());
    // assertEquals("SamplePassword", savedUser.getPassword());
    // assertEquals("SampleRole", savedUser.getRole());
    // }

    // @Test
    // public void testFindByUsername() {
    // // Create and save multiple User instances
    // User user1 = new User();
    // user1.setUsername("Username1");
    // user1.setPassword("Password1");
    // user1.setRole("Role1");
    // // Set other fields as needed

    // User user2 = new User();
    // user2.setUsername("Username2");
    // user2.setPassword("Password2");
    // user2.setRole("Role2");
    // // Set other fields as needed

    // userRepository.save(user1);
    // userRepository.save(user2);

    // // Retrieve Users by username
    // User userWithUsername1 =
    // userRepository.findByUsername("Username1").orElse(null);

    // // Perform assertions
    // assertNotNull(userWithUsername1);
    // assertEquals("Username1", userWithUsername1.getUsername());
    // assertEquals("Password1", userWithUsername1.getPassword());
    // assertEquals("Role1", userWithUsername1.getRole());
    // }
}