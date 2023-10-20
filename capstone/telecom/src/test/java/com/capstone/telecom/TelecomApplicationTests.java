package com.capstone.telecom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.capstone.telecom.controller.WinmController;
import com.capstone.telecom.entity.ICCID;
import com.capstone.telecom.repository.CustomerRepository;
import com.capstone.telecom.repository.ICCIDRepository;
import com.capstone.telecom.repository.IMEIRepository;
import com.capstone.telecom.repository.MSISDNRepository;
import com.capstone.telecom.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
public class TelecomApplicationTests {

    @Test
    void contextup(){
        
    }

    // @MockBean
    // private ICCIDRepository iccidRepository;

    // @Autowired
    // private MockMvc mockMvc;

    // @MockBean
    // private ObjectMapper objectMapper;

    // @BeforeEach
    // void setUp() {
    //     when(iccidRepository.findAll()).thenReturn(new ArrayList<>());
    // }

    // @Test
    // @WithMockUser
    // void testCreateICCID() throws JsonProcessingException, Exception  {
    //     ICCID iccid = new ICCID();
    //     iccid.setIccidNumber("12345678901234567890");

    //     MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/iccid")
    //             .content("application/json")
    //             .content(objectMapper.writeValueAsString(iccid))).andReturn();

    //     int status = result.getResponse().getStatus();
    //     assertEquals(HttpStatus.OK.value(),status);
    // }
}