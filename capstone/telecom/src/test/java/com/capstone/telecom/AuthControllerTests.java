// package com.capstone.telecom;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

// import com.capstone.telecom.business.LoginBody;
// import com.capstone.telecom.service.UserService;

 

// @SpringBootTest

// @AutoConfigureMockMvc


// @ExtendWith(MockitoExtension.class)

// public class AuthControllerTests {

 

//         @Autowired

//         private MockMvc mockMvc;

 

//         @MockBean

//         private UserService userService;

 

//         @MockBean

//         private AuthenticationManager authenticationManager;

 

//         @Autowired

//         private ObjectMapper objectMapper;

 

//         @Test

//         void registeringUser() throws Exception {

//                 SignupBody signupBody = SignupBody.builder()

//                                 .username("test")

//                                 .password("test123")

//                                 .email("test123@gmail.com").build();

 

//                 mockMvc.perform(post("/api/auth/signup").contentType(MediaType.APPLICATION_JSON)

//                                 .content(objectMapper.writeValueAsString(signupBody)))

//                                 .andExpect(status().isCreated())

//                                 .andExpect(jsonPath("$.message").value("User registered successfully!"));

//         }


//         @Test

//         public void testUserAuthentication() throws Exception {

//                 LoginBody loginBody = new LoginBody("testUser", "testPassword");

 

//                 List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

//                 Authentication auth = new UsernamePasswordAuthenticationToken("testUser", "testPassword", authorities);

 

//                 when(authenticationManager

//                                 .authenticate(new UsernamePasswordAuthenticationToken("testUser", "testPassword")))

//                                 .thenReturn(auth);

//                 SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

//                 securityContext.setAuthentication(auth);

//                 SecurityContextHolder.setContext(securityContext);

 

//                 mockMvc.perform(MockMvcRequestBuilders

//                                 .post("/api/auth/token")

//                                 .contentType(MediaType.APPLICATION_JSON)

//                                 .content(objectMapper.writeValueAsString(loginBody)))

//                                 .andExpect(status().isOk())

//                                 .andExpect(content().contentType(MediaType.APPLICATION_JSON))

//                                 .andExpect(jsonPath("$.token").isNotEmpty());

//         }

// 	}
