package com.example.demo.models.ForUser;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "roleId")})
    private List<Role> roles = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userId")
    private UserInfo userInfo;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userId")
    private Wallet wallet;

    public void setRoles(Role roles) {
        this.roles.add(roles);
    }
}