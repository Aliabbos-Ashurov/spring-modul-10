package com.pdp.springm10.service;

import com.pdp.springm10.dto.UpdateUserDTO;
import com.pdp.springm10.dto.UserDTO;
import com.pdp.springm10.entity.User;
import com.pdp.springm10.handler.exception.UserNotFoundException;
import com.pdp.springm10.mapper.UserMapper;
import com.pdp.springm10.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
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
    private final UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void saveUsers(List<UserDTO> dtoList) {
        dtoList.forEach(dto -> {
            User user = userMapper.toUser(dto);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
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
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }
}
