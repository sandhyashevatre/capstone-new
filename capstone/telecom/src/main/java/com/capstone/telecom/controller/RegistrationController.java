package com.capstone.telecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capstone.telecom.business.UserForm;
import com.capstone.telecom.entity.User;
import com.capstone.telecom.service.UserService;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public String getRegistrationForm(Model model) {
        if (!model.containsAttribute("userForm")) {
            model.addAttribute("userForm", new UserForm());
        }
        return "register";
    }

    @PostMapping
    public String register(@RequestBody UserForm userForm
    		) {
        User userToCreate = new User();
        userToCreate.setUsername(userForm.getName());
        userToCreate.setPassword(userForm.getPassword());
        userToCreate.setRole(userForm.getRole());
        System.out.println(userForm.getName());

        userService.create(userToCreate);
      
        return "redirect:/loginpage";
    }
}
