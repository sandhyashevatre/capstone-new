package com.capstone.telecom.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capstone.telecom.business.RandomICCID;
import com.capstone.telecom.dto.InsertSimDTO;
import com.capstone.telecom.dto.ReservationDTO;
import com.capstone.telecom.dto.SimDTO;
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
        Customer customer = getOrCreateCustomer(reservationDTO.getCustomerName());

        MSISDN existingMsisdn = msisdnRepository.findBymsisdnNumber(reservationDTO.getReservingNumber()).orElse(null);

        if (existingMsisdn != null) {

            return ResponseEntity.ok(false);

        }
        Registration sim = createReservation(reservationDTO, customer);

        if (sim == null)

            return ResponseEntity.ok(false);

        else

            return ResponseEntity.ok(true);
    }

    private Registration createReservation(ReservationDTO reservationDTO, Customer customer) {

        ICCID newIccid = new ICCID();

        newIccid.setIccidNumber(RandomICCID.generate(reservationDTO.getProvider()));

        newIccid.setNetworkProvider(reservationDTO.getProvider());

        System.out.println(newIccid.getIccidNumber());

        iccidRepository.save(newIccid);

        MSISDN newMsisdn = new MSISDN();

        newMsisdn.setMsisdnNumber(reservationDTO.getReservingNumber());

        msisdnRepository.save(newMsisdn);

        Registration newSim = new Registration();

        newSim.setIccid(newIccid);

        newSim.setMsisdn(newMsisdn);

        newSim.setRegistrationtTime(LocalDateTime.now());

        if (customer.getRegistrations() == null) {

            customer.setRegistrations(new ArrayList<>());

        }

        newSim.setConnectionType(reservationDTO.getConnectionType());

        customer.getRegistrations().add(newSim);

        customerRepository.save(customer);

        return newSim;

    }

    @PostMapping("/insertSim")

    public ResponseEntity<Boolean> insertSimInto(@RequestBody InsertSimDTO insertSimDTO) {

        System.out.println(insertSimDTO);

        Boolean status = setSIMInPhoneWithIMEI(insertSimDTO.getImei(), insertSimDTO.getMsisdn());

        return ResponseEntity.ok(status);

    }

    @PostMapping("/changeProvider")

    public ResponseEntity<Boolean> changeProviderofRegistration(@RequestBody ReservationDTO reservationDTO) {

        Boolean status = changeProvider(reservationDTO);

        return ResponseEntity.ok(status);

    }

    @GetMapping("/allprepaidsims")
    public ResponseEntity<List<SimDTO>> getAllThePrepaidSims() {
        List<Registration> sims = getAllPrepaidSims();
        if (sims != null) {
            List<SimDTO> simDTOs = convertToDTO(sims);
            return ResponseEntity.ok(simDTOs);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/allpostpaidsims")
    public ResponseEntity<List<SimDTO>> getAllThePostpaidSims() {
        List<Registration> sims = getAllPostpaidSims();
        if (sims != null) {
            List<SimDTO> simDTOs = convertToDTO(sims);
            return ResponseEntity.ok(simDTOs);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
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

    public boolean imeiAlreadyExist(String imei) {

        List<Customer> customers = customerRepository.findAll();

        for (Customer customer : customers) {

            for (Registration sim : customer.getRegistrations()) {

                if (sim.getImei() == null) {

                    return false;

                }

                if (sim.getImei().getImeiNumber().equals(imei))

                    return true;

            }

        }

        return false;

    }

    public Boolean setSIMInPhoneWithIMEI(String imeiNumber, String phoneNumber) {

        List<Customer> customers = customerRepository.findAll();

        for (Customer customer : customers) {

            for (Registration sim : customer.getRegistrations()) {

                if (imeiAlreadyExist(imeiNumber)) {

                    return false;

                }

                if (sim.getMsisdn().getMsisdnNumber().equals(phoneNumber)) {

                    IMEI newImei = new IMEI();

                    newImei.setImeiNumber(imeiNumber);

                    sim.setImei(newImei);

                    imeiRepository.save(newImei);

                    customerRepository.save(customer);

                    return true;

                }

            }

        }

        return false;

    }

    public List<Registration> getAllPrepaidSims() {

        List<Registration> allSims = new ArrayList<>();

        List<Customer> customers = customerRepository.findAll();

        for (Customer customer : customers) {

            for (Registration sim : customer.getRegistrations()) {

                if (sim.getConnectionType().equals("prepaid"))

                    allSims.add(sim);

            }

        }

        return allSims;

    }

    public Boolean changeProvider(ReservationDTO reservationDTO) {

        Customer customer = getOrCreateCustomer(reservationDTO.getCustomerName());

        Boolean status = changeProviderTo(reservationDTO.getReservingNumber(), reservationDTO.getProvider(), customer,
                reservationDTO.getConnectionType());

        return status;

    }

    public Boolean changeProviderTo(String msisdn, String provider, Customer customer, String connectionType) {

        Boolean flag = false;

        for (Registration sim : customer.getRegistrations()) {

            if (sim.getMsisdn().getMsisdnNumber().equals(msisdn)) {

                if (sim.getIccid().getNetworkProvider().equals(provider))

                    return flag;

                else {

                    ICCID newIccid = new ICCID();

                    newIccid.setIccidNumber(RandomICCID.generate(provider));

                    newIccid.setNetworkProvider(provider);

                    sim.setIccid(newIccid);

                    sim.setConnectionType(connectionType);

                    sim.setActivated(false);

                    sim.setRegistrationtTime(LocalDateTime.now());

                    iccidRepository.save(newIccid);

                    flag = true;

                }

            }

        }

        customerRepository.save(customer);

        return flag;

    }

    public List<Registration> getAllPostpaidSims() {

        List<Registration> allSims = new ArrayList<>();

        List<Customer> customers = customerRepository.findAll();

        for (Customer customer : customers) {

            for (Registration sim : customer.getRegistrations()) {

                if (sim.getConnectionType().equals("postpaid"))

                    allSims.add(sim);

            }

        }

        return allSims;

    }

    public List<SimDTO> convertToDTO(List<Registration> sims) {

        List<SimDTO> simDTOs = new ArrayList<>();

        for (Registration sim : sims) {

            SimDTO simDTO = new SimDTO();

            simDTO.setId(sim.getId());

            simDTO.setIccid(sim.getIccid().getIccidNumber());

            simDTO.setMsisdn(sim.getMsisdn().getMsisdnNumber());

            simDTO.setImei(sim.getImei() == null ? "" : sim.getImei().getImeiNumber());

            simDTO.setActivated(sim.isActivated());

            simDTO.setReservationDateTime(sim.getRegistrationtTime().toLocalDate().toString());

            simDTOs.add(simDTO);

        }

        return simDTOs;

    }

    public Customer getOrCreateCustomer(String customerName) {

        return customerRepository.findByName(customerName).orElseGet(() -> {

            Customer newCustomer = new Customer();

            newCustomer.setName(customerName);

            customerRepository.save(newCustomer);

            return newCustomer;

        });

    }

}
