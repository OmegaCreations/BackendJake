package com.walicki.BackendJake.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Document(collection = "users")
@Data
public class UserEntity implements UserDetails {

    // User id
    @Id
    private String id;

    // Name
    @Setter
    private String username;

    // Password
    @Setter
    @Getter
    private String password;

    // Highscores
    @Setter
    private List<Integer> highscores;

    // Snake color
    @Setter
    @Getter
    private String snake_color;

    // Coffees drank
    @Setter
    private Integer coffees_drank;

    @Setter
    @Getter
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
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
