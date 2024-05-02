package com.example.demo.models.ForUser;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userInfoId;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User userId;

    private String name;
    private String surname;

    @Column(unique = true)
    private String tg;

    private LocalDate dateOfBirth;
}
