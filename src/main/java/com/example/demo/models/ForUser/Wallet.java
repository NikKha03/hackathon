package com.example.demo.models.ForUser;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "wallet")

public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walletId;

    private String currency = "CloudCoin";

    private int quantity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User userId;
}
