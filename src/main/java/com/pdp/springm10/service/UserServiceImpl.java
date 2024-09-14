package com.pdp.springm10.service;

import com.pdp.springm10.dto.UpdateUserDTO;
import com.pdp.springm10.dto.UserDTO;
import com.pdp.springm10.entity.User;
import com.pdp.springm10.handler.exception.UserNotFoundException;
import com.pdp.springm10.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  12:00
 **/
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User save(UserDTO dto) {
        User user = User.builder()
                .fullname(dto.fullname())
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .build();
        return userRepository.save(user);
    }

    @Override
    @Cacheable(value = "users", key = "#id")
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        return new UserDTO(user.getFullname(), user.getUsername(), user.getPassword());
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void saveUsers(List<UserDTO> dtoList) {
        dtoList.forEach(dto -> {
            User user = User.builder()
                    .fullname(dto.fullname())
                    .username(dto.username())
                    .password(passwordEncoder.encode(dto.password()))
                    .build();
            userRepository.save(user);
        });
    }

    @Override
    public User update(UpdateUserDTO dto) {
        User userToUpdate = userRepository.findById(dto.id())
                .orElseThrow(() -> new UserNotFoundException(":::: User not found with id: {0} ::::", dto.id()));
        userToUpdate.setFullname(dto.fullname());
        userToUpdate.setUsername(dto.username());
        userToUpdate.setPassword(passwordEncoder.encode(dto.password()));
        return userRepository.save(userToUpdate);
    }

    @Override
    @Cacheable(value = "users", key = "#root.methodName")
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(user -> {
                    return new UserDTO(user.getFullname(), user.getUsername(), user.getPassword());
                })
                .collect(Collectors.toList());
    }
}
