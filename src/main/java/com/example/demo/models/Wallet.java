package com.example.demo.models;

import com.example.demo.models.ForUser.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Wallet")

public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wallet_id;

    @Column
    private String walletName = "CloudCoin";

    @Column(nullable = false)
    private int walletQuantity;

    @OneToMany(mappedBy = "walletQuantity")
    private List<Customer> walletQuantity_list;

    @OneToMany(mappedBy = "wallet_id")
    private List<Customer> wallet_id_list;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User userId;
}
