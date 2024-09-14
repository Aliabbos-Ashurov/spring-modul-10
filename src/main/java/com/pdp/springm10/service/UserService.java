package com.pdp.springm10.service;

import com.pdp.springm10.dto.UpdateUserDTO;
import com.pdp.springm10.dto.UserDTO;
import com.pdp.springm10.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  11:55
 **/
public interface UserService {

    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User saveUser(User user);

    void saveUsers(List<UserDTO> users);

    List<UserDTO> findAll();

    User findById(Long id);

    void deleteById(Long id);

    User update(UpdateUserDTO dto);
}

