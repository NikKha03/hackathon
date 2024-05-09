package com.example.demo.models.ForUser;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;

    @Column(nullable = false, unique = true)
    private String roleName;

//    @ManyToMany(mappedBy = "roles")
//    private List<User> users;
}
