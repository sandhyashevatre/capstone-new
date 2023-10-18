package com.capstone.telecom.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capstone.telecom.business.RandomICCID;
import com.capstone.telecom.business.ReservationDTO;
import com.capstone.telecom.entity.Customer;
import com.capstone.telecom.entity.ICCID;
import com.capstone.telecom.entity.IMEI;
import com.capstone.telecom.entity.MSISDN;
import com.capstone.telecom.entity.Registration;
import com.capstone.telecom.entity.User;
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

    @PostMapping("/reservation")
    public ResponseEntity<Boolean> reserveNumber(@RequestBody ReservationDTO reservationDTO) {
        System.out.println(reservationDTO);
        Customer customer = customerRepository.findByName(reservationDTO.getCustomerName()).orElse(new Customer());

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
        newRegistration.setRegistrationtTime(LocalDateTime.now());
        if (customer.getRegistrations().size() != 0) {
            customer.getRegistrations().add(newRegistration);
            customerRepository.save(customer);
            return ResponseEntity.ok(true);
        } else {
            customer.setRegistrations(new ArrayList<>());
            customer.getRegistrations().add(newRegistration);
            customerRepository.save(customer);
            return ResponseEntity.ok(false);
        }
    }

    public Boolean activate() {

        LocalDateTime currentDateTime = LocalDateTime.now();

        List<Registration> registrationToActivate = new ArrayList<>();

        for (Customer customer : customerRepository.findAll()) {

            if (customer.getRegistrations() != null) {

                for (Registration registration : customer.getRegistrations()) {

                    LocalDateTime reservationDateTime = registration.getRegistrationtTime();

                    if (reservationDateTime.plusDays(1).isBefore(currentDateTime)) {

                        registrationToActivate.add(registration);

                    }

                }

            }

        }

        for (Registration sim : registrationToActivate) {

            activateReservation(sim);

        }

        return true;

    }

    private void activateReservation(Registration registration) {

        registration.setActivated(true);

        iccidRepository.save(registration.getIccid());

        msisdnRepository.save(registration.getMsisdn());

    }

}
