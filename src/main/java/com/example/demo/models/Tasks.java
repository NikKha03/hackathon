package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tasks")

public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;

    @Column(nullable = false)
    private String taskTitle;

    @Column
    private String taskDescription;

    @Column(nullable = false)
    private int taskCost;

    @Column(nullable = false)
    private int taskXp;
}