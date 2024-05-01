package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Perchase")

public class Perchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long perchase_id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer_id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Store product_id;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet_id;

    @ManyToOne
    @JoinColumn(name = "product_cost")
    private Store product_cost;
}
