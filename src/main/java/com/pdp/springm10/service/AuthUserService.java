package com.pdp.springm10.service;

import com.pdp.springm10.dto.AuthUserCreateDTO;
import com.pdp.springm10.dto.AuthUserCriteriaDTO;
import com.pdp.springm10.dto.AuthUserGetDTO;
import com.pdp.springm10.dto.AuthUserUpdateDTO;
import com.pdp.springm10.entity.AuthUser;
import com.pdp.springm10.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aliabbos Ashurov
 * @since 15/September/2024  14:40
 **/
@Service
@RequiredArgsConstructor
public class AuthUserService {

    private final AuthUserRepository repository;

    public void create(AuthUserCreateDTO dto) {
        AuthUser user = new AuthUser(0L, dto.name(), dto.email(), dto.password());
        repository.save(user);
    }

    public void update(AuthUserUpdateDTO dto) {
        AuthUser user = repository.findById(dto.id())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setName(dto.name());
        user.setEmail(dto.email());
        repository.save(user);
    }

    public void delete(String id) {
        repository.deleteById(Long.valueOf(id));
    }

    public AuthUserGetDTO get(String id) {
        AuthUser user = repository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return new AuthUserGetDTO(user.getId(), user.getName(), user.getEmail());
    }

    public List<AuthUserGetDTO> list(AuthUserCriteriaDTO criteria) {
        List<AuthUser> users = repository.findAll();
        return users.stream()
                .map(user -> new AuthUserGetDTO(user.getId(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }
}
