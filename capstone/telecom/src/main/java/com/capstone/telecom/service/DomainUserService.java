package com.capstone.telecom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstone.telecom.entity.User;
import com.capstone.telecom.repository.UserRepository;

@Service
public class DomainUserService {

    private static final String ENCODING_STRATEGY = "{bcrypt}";

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DomainUserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Optional<User> getByName(String name) {
        return userRepository.findByUsername(name);
    }

    private String prefixEncodingStrategyAndEncode(String rawString) {
        return ENCODING_STRATEGY + passwordEncoder.encode(rawString);
    }

    public User save(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(prefixEncodingStrategyAndEncode(password));
        user.setRole(role);
        return userRepository.save(user);
    }

}