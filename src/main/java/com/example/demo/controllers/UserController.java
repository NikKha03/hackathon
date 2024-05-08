package com.example.demo.controllers;

import com.example.demo.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasAuthority('USER')")
@RequestMapping("/hackathon/user")
@AllArgsConstructor
public class UserController {
    private WalletRepository walletRepository;
}
