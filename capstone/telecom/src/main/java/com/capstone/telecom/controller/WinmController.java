package com.capstone.telecom.controller;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capstone.telecom.business.RandomICCID;
import com.capstone.telecom.business.ReservationDTO;
import com.capstone.telecom.entity.Address;
import com.capstone.telecom.entity.Customer;
import com.capstone.telecom.entity.ICCID;
import com.capstone.telecom.entity.IMEI;
import com.capstone.telecom.entity.MSISDN;
import com.capstone.telecom.entity.Registration;
import com.capstone.telecom.entity.User;
import com.capstone.telecom.repository.AddressRepository;
import com.capstone.telecom.repository.CustomerRepository;
import com.capstone.telecom.repository.ICCIDRepository;
import com.capstone.telecom.repository.IMEIRepository;
import com.capstone.telecom.repository.MSISDNRepository;
import com.capstone.telecom.repository.UserRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class WinmController {

    @Autowired
    private ICCIDRepository iccidRepository;

    @Autowired
    private MSISDNRepository msisdnRepository;

    @Autowired
    private IMEIRepository imeiRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // ICCID Endpoints
    @GetMapping("/iccid")
    public List<ICCID> getAllICCID() {
        return iccidRepository.findAll();
    }

    @PostMapping("/iccid")
    public ICCID createICCID(@RequestBody ICCID iccid) {
        return iccidRepository.save(iccid);
    }

    // MSISDN Endpoints
    @GetMapping("/msisdn")
    public List<MSISDN> getAllMSISDN() {
        return msisdnRepository.findAll();
    }

    @PostMapping("/msisdn")
    public MSISDN createMSISDN(@RequestBody MSISDN msisdn) {
        return msisdnRepository.save(msisdn);
    }

    // IMEI Endpoints
    @GetMapping("/imei")
    public List<IMEI> getAllIMEI() {
        return imeiRepository.findAll();
    }

    @PostMapping("/imei")
    public IMEI createIMEI(@RequestBody IMEI imei) {
        return imeiRepository.save(imei);
    }

    // User Endpoints
    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Address Endpoints
    @GetMapping("/address")
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @PostMapping("/address")
    public Address createAddress(@RequestBody Address address) {
        return addressRepository.save(address);
    }

    @PostMapping("/reservation")
    public ResponseEntity<Boolean> reserveNumber(@RequestBody ReservationDTO reservationDTO){
        System.out.println(reservationDTO);
        Customer customer = customerRepository.findByName(reservationDTO.getCustomerName()).orElse(new Customer());
        // return ResponseEntity.ok(true);
        customer.setName(reservationDTO.getCustomerName());
        customer.setAadharNumber("");
        customer.setFirstname("");
        customer.setLastname("");
        Registration newRegistration = new Registration();
            MSISDN newMsisdn = msisdnRepository.findBymsisdnNumber(reservationDTO.getPhoneNumber()).orElse(new MSISDN());
            newMsisdn.setMsisdnNumber(reservationDTO.getPhoneNumber());
            msisdnRepository.save(newMsisdn);
            newRegistration.setMsisdn(newMsisdn);
            ICCID newIccid = new ICCID();
            newIccid.setIccidNumber(RandomICCID.generate(reservationDTO.getProvider()));
            iccidRepository.save(newIccid);
            newRegistration.setIccid(newIccid);
        if(customer.getRegistrations().size()!=0){
            customer.getRegistrations().add(newRegistration);
            customerRepository.save(customer);
            return ResponseEntity.ok(true);
        }
        else{
            customer.setRegistrations(new ArrayList<>());
            customer.getRegistrations().add(newRegistration);
            customerRepository.save(customer);
            return ResponseEntity.ok(false);
        }
    }

    // @PostMapping("/sim-registration")
    // public ResponseEntity<String> registerSimCard(@RequestBody SimCardRegistrationRequest request) {
    //     // Validate the request
    //     if (!isValidRegistrationRequest(request)) {
    //         return ResponseEntity.badRequest().body("Invalid registration request.");
    //     }
        
    //     // Implement logic to categorize the SIM card based on user data and business rules
    //     String category = categorizeSimCard(request);
        
    //     // Generate a unique issuer number for the SIM card
    //     String issuerNumber = generateIssuerNumber();
        
    //     // Calculate bandwidth based on category (this can be based on predefined values)
    //     double bandwidth = calculateBandwidth(category);
        
    //     // Save the SIM card information to the database
    //     SimCard simCard = new SimCard(category, issuerNumber, bandwidth, request.getUserId());
    //     simCardRepository.save(simCard);
        
    //     // Return a success response
    //     return ResponseEntity.ok("SIM card registered successfully.");
    // }

    // private boolean isValidRegistrationRequest(SimCardRegistrationRequest request) {
    //     // Add validation logic here to ensure the request is valid
    //     // For example, check if the user ID is valid and other necessary data is provided
    //     return request.getUserId() != null && request.getOtherData() != null;
    // }


    // @PostMapping("/sim-porting")
    // public ResponseEntity<String> portSimCard(@RequestBody SimCardPortingRequest request) {
    // // Validate the request
    // if (!isValidPortingRequest(request)) {
    //     return ResponseEntity.badRequest().body("Invalid porting request.");
    // }
    
    // // Check if the SIM card to be ported exists and is eligible for porting
    // SimCard simCardToPort = simCardRepository.findByIssuerNumber(request.getIssuerNumber());
    // if (simCardToPort == null || !isEligibleForPorting(simCardToPort)) {
    //     return ResponseEntity.badRequest().body("Invalid SIM card for porting.");
    // }
    
    // // Implement logic for SIM card porting, such as transferring user data and deactivating the old SIM card
    // performSimCardPorting(simCardToPort, request);
    
    // // Return a success response
    // return ResponseEntity.ok("SIM card ported successfully.");
    // }

    // private boolean isValidPortingRequest(SimCardPortingRequest request) {
    //     // Add validation logic here to ensure the request is valid
    //     // For example, check if the porting request contains necessary data
    //     return request.getIssuerNumber() != null && request.getOtherData() != null;
    // }

    // private boolean isEligibleForPorting(SimCard simCard) {
    //     // Implement eligibility criteria for porting, e.g., check if the SIM card is active and not expired
    //     return simCard.isActive() && !simCard.isExpired();
    // }

}
