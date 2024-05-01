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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "customer_id")
    private List<Perchase> customer_list = new ArrayList<>();

    @JoinColumn(name = "walletQuantity", nullable = false)
    private int walletQuantity;

    @JoinColumn(name = "wallet_id", nullable = false)
    private Long wallet_id;

    @JoinColumn(name = "userName", unique = true, nullable = false)
    private String userName;

    @JoinColumn(name = "userSurname", unique = true, nullable = false)
    private String userSurname;
}
