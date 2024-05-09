package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tasks")

public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    private int cost;

    @Column(nullable = false)
    private int experience;
}