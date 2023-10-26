package com.capstone.telecom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.capstone.telecom.entity.User;
import com.capstone.telecom.exception.TelecomBusinessException;
import com.capstone.telecom.repository.UserRepository;
import com.capstone.telecom.service.CustomUserDetailsService;
import com.capstone.telecom.service.DomainUserService;
import com.capstone.telecom.service.UserService;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

public class ServiceTests {

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCustomUserDetailsService_LoadUserByUsername_UserFound() {
        CustomUserDetailsService customUserDetailsService = new
        CustomUserDetailsService();
        String username = "testUser";
        User user = new User();
        user.setUsername(username);
        user.setPassword("{bcrypt}$2a$10$testPasswordHash");
        user.setRole("ROLE_USER");
        when(userService.getByName(username)).thenReturn(Optional.of(user));

        UserDetails userDetails =
        customUserDetailsService.loadUserByUsername(username);

        assertEquals(username, userDetails.getUsername());
        // Add more assertions for roles, passwords, etc.
    }

    @Test
    public void testCustomUserDetailsService_LoadUserByUsername_UserNotFound() {
        CustomUserDetailsService customUserDetailsService = new
        CustomUserDetailsService();
        String username = "nonExistentUser";
        when(userService.getByName(username)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () ->
        customUserDetailsService.loadUserByUsername(username));
    }

    @Test
    public void testDomainUserService_Save() {
        DomainUserService domainUserService = new DomainUserService(userRepository);
        String username = "testUser";
        String password = "testPassword";
        String role = "ROLE_USER";
        User savedUser = new User();
        savedUser.setUsername(username);
        savedUser.setPassword("{bcrypt}$2a$10$testPasswordHash");
        savedUser.setRole(role);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User user = domainUserService.save(username, password, role);

        assertEquals(username, user.getUsername());
        // Add more assertions for password, role, etc.
        assertTrue(user.getPassword().startsWith("{bcrypt}"));
    }

  

    @Test
    public void testUserService_Authenticate_InvalidUser() {
        UserService userService = new UserService(userRepository);
        String username = "testUser";
        String password = "incorrectPassword";
        User user = new User();
        user.setUsername(username);
        user.setPassword("{bcrypt}$2a$10$testPasswordHash");
        user.setRole("ROLE_USER");
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        Optional<User> authenticatedUser = userService.authenticate(username, password);

        assertTrue(authenticatedUser.isEmpty());
    }
///

@Test
void testGetUserById_ExistingUser() {
    // Arrange
    long userId = 1L;
    User user = new User();
    user.setUsername("testUser");
    user.setPassword("testPassword");
    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    
    // Act
    Optional<User> foundUser = userService.getById(userId);
    
    // Assert
    assertTrue(foundUser.isPresent());
    assertEquals(user, foundUser.get());
}

@Test
void testGetUserById_NonExistentUser() {
    // Arrange
    long userId = 2L;
    when(userRepository.findById(userId)).thenReturn(Optional.empty());
    
    // Act
    Optional<User> foundUser = userService.getById(userId);
    
    // Assert
    assertTrue(foundUser.isEmpty());
    }

    @Test
    void testCreateUser() {
        // Arrange
         User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        when(userRepository.save(user)).thenReturn(user);
        
        // Act
        User createdUser = userService.create(user);
        
        // Assert
        assertEquals(user, createdUser);
        assertEquals("{bcrypt}" + passwordEncoder.encode("password"), createdUser.getPassword());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testAuthenticate_ExistingUserAndPasswordMismatch() {
        // Arrange
        String username = "testUser";
        String password = "{bcrypt}" + passwordEncoder.encode("differentPassword");
         User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        
        // Act
        Optional<User> authenticatedUser = userService.authenticate(username, password);
        
        // Assert
        assertTrue(authenticatedUser.isEmpty());
    }

    @Test
    void testAuthenticate_UserNotFound() {
        // Arrange
        String username = "nonExistentUser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        
        // Act & Assert
        assertThrows(TelecomBusinessException.class, () -> {
            userService.authenticate(username, "somePassword");
        });
        
}
}