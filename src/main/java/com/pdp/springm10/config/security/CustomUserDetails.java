package com.pdp.springm10.config.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  13:03
 **/
@Getter
public class CustomUserDetails implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;

    private CustomUserDetails(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public static CustomUserDetails of(Long id, String username, String password) {
        return new CustomUserDetails(id, username, password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

}
