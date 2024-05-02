package com.example.demo.models.ForUser;

import com.example.demo.models.Wallet;
import jakarta.persistence.*;
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

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "roleId")})
    private List<Role> roles = new ArrayList<>();

    @OneToOne(mappedBy = "userId")
    private UserInfo userInfo;

    @OneToMany(mappedBy = "user_id")
    private List<Wallet> WalletList;

    public void setRoles(Role roles) {
        this.roles.add(roles);
    }

    public String getRoles() {
        return this.roles.toString();
    }
}