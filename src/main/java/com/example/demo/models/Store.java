package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nameProduct;

    @Column(nullable = false)
    private int cost;

    @OneToMany(mappedBy = "product_cost")
    private List<Perchase> product_cost_list;

    @OneToMany(mappedBy = "product_id")
    private List<Perchase> product_list;

}
