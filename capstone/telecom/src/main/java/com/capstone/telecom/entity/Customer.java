package com.capstone.telecom.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//name
    private String name;

    @Size(max = 50,message = "Enter your firstname within 50 words")
    private String firstname;

    @Size(max = 50,message = "Enter your lastname within 50 words")
    private String lastname;

    @Size(max = 12,message = "Enter your valid aadhar number")
    private String aadharNumber;

    @OneToOne
    private Address address;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Registration> registrations = new ArrayList<>();
}
