package com.capstone.telecom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.capstone.telecom.entity.User;
import com.capstone.telecom.repository.UserRepository;
import com.capstone.telecom.service.DomainUserService;
import com.capstone.telecom.service.UserService;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
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

    @Test
    void testGetUserById_NonExistentUser() {

        long userId = 2L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<User> foundUser = userService.getById(userId);

        assertTrue(foundUser.isEmpty());
    }

    @Test
    void testAuthenticate_ExistingUserAndPasswordMismatch() {

        String username = "testUser";
        String password = "{bcrypt}" + passwordEncoder.encode("differentPassword");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        Optional<User> authenticatedUser = userService.authenticate(username, password);

        assertTrue(authenticatedUser.isEmpty());
    }

    @Test
    void testCreateUser() {
        // Arrange
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        // Set a default role (e.g., "USER") for the user
        user.setRole("USER");

        // Mock the repository save behavior
        when(userRepository.save(user)).thenReturn(user);

    }

    @Test
    void testAuthenticate_UserNotFound() {
        // Arrange
        String username = "nonExistentUser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act & Assert
        userService.authenticate(username, "somePassword");
    };

}