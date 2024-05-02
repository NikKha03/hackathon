package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Customer")

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;

    @OneToMany(mappedBy = "customer_id")
    private List<Perchase> customer_list;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet_id;

    @ManyToOne
    @JoinColumn(name = "walletQuantity")
    private Wallet walletQuantity;

}
