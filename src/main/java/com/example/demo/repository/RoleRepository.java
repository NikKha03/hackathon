package com.example.demo.repository;

import com.example.demo.models.ForUser.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleByRoleName(String role);
}
