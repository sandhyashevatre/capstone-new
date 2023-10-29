package com.capstone.telecom;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.test.context.support.WithMockUser;
// import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
// import org.springframework.web.cors.CorsConfiguration;

// import com.capstone.telecom.business.LoginBody;
// import com.capstone.telecom.controller.APIAuthController;
// import com.capstone.telecom.dto.TokenDTO;

// import jakarta.servlet.http.HttpServletRequest;

// import org.mockito.Mock;
// import org.mockito.Mockito;

// @WebMvcTest(APIAuthController.class)
// public class APIAuthControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private AuthenticationManager authenticationManager;

//     @MockBean
//     private CorsConfig corsConfig;

//     @Test
//     @WithMockUser
//     void testTokenEndpoint() throws Exception {
//         // Arrange
//         LoginBody loginBody = new LoginBody();
//         loginBody.setUsername("username");
//         loginBody.setPassword("password");
    
//         // Mock the authentication manager behavior
//         UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword());
//         Mockito.when(authenticationManager.authenticate(authenticationToken)).thenReturn(authenticationToken);
    
//         // Act & Assert
//         mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/token")
//                 .with(SecurityMockMvcRequestPostProcessors.csrf())
//                 .contentType("application/json") 
//                 .content("{\"username\":\"username\",\"password\":\"password\"}"))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists());
//     }
    
//     @Test
//     @WithMockUser
//     public void testCorsConfiguration() {
//         HttpServletRequest request = null; 
//         CorsConfiguration corsConfiguration = corsConfig.getCorsConfiguration(request);

//         assertNotNull(corsConfiguration);
//         assertTrue(corsConfiguration.getAllowedOrigins().contains("http://localhost:3000"));
//         assertTrue(corsConfiguration.getAllowedMethods().contains("*"));
//         assertTrue(corsConfiguration.getAllowedHeaders().contains("*"));
//     }
// }



