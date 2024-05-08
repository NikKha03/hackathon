package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;


@Data
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Double price;
    private long customer_id;
}