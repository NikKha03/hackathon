package com.example.demo.config;

import com.example.demo.models.ForUser.Role;
import com.example.demo.models.ForUser.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class MyUserDetails implements UserDetails {
    private Long userId;
    private String email;
    private String username;
    private String password;
    private List<String> roles;


    public static MyUserDetails build(User user) {
        List<String> userRoles = user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList());
        return new MyUserDetails(
                user.getUserId(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                userRoles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
