package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String category;

    private String name;

    private int cost;
}
