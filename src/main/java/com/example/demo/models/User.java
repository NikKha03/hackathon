package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String surname;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String tg;

    private LocalDate dateOfBirth;

    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name="user_role")
    private List<Role> roles;

    @OneToMany(mappedBy = "user_id")
    private List<Wallet> WalletList;
}