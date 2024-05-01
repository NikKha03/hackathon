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

    @JoinColumn(name = "customer_id", unique = true, nullable = false)
    private Long customer_id;

    @JoinColumn(name = "product_id", nullable = false)
    private int cost;

    @JoinColumn(name = "wallet_id", nullable = false)
    private Long wallet_id;
}
