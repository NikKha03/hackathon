package com.example.demo.controllers;

import com.example.demo.models.ForUser.User;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/hackathon/admin")
@AllArgsConstructor
public class AdminController {
    UserRepository userRepository;

    @GetMapping("/all-users")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
