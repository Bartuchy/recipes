package com.markiewicz.recipes.security;

import com.markiewicz.recipes.user.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private String email;
    private String password;
    private final List<SimpleGrantedAuthority> authorities = new ArrayList<>();

    public UserDetailsImpl(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();

        user
                .getRoles()
                .forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
