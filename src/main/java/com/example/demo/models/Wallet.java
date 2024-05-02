package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Wallet")

public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wallet_id;

//    @Column
//    private String walletName;
//
//    @Column(nullable = false)
//    private int walletQuantity;
//
//    @JoinColumn(name = "user_id", unique = true, nullable = false)
//    private Long user_id;
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "walletQuantity")
//    private List<Customer> walletQuantity_list = new ArrayList<>();
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "wallet_id")
//    private List<Customer> wallet_id_list = new ArrayList<>();
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "wallet_id")
//    private List<Customer> wallet_ID_list = new ArrayList<>();
}
