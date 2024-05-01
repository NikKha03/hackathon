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

    @Column
    private String walletName;

    @Column(nullable = false)
    private int walletQuantity;

    @OneToMany(mappedBy = "walletQuantity")
    private List<Customer> walletQuantity_list;

    @OneToMany(mappedBy = "wallet_id")
    private List<Customer> wallet_id_list;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;
}
