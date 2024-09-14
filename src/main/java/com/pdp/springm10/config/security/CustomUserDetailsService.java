package com.pdp.springm10.config.security;

import com.pdp.springm10.entity.User;
import com.pdp.springm10.handler.exception.UserNotFoundException;
import com.pdp.springm10.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  13:05
 **/
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final AuthUserRepository authUserRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authUserRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with name: {0}", username));
        return CustomUserDetails.of(user.getId(), user.getUsername(), user.getPassword());
    }
}
