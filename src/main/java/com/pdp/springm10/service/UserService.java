package com.pdp.springm10.service;

import com.pdp.springm10.dto.UpdateUserDTO;
import com.pdp.springm10.dto.UserDTO;
import com.pdp.springm10.entity.User;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  11:55
 **/
public interface UserService {

    User findByUsername(String username);

    boolean existsByUsername(String username);

    User save(UserDTO dto);

    void saveUsers(List<UserDTO> users);

    List<UserDTO> findAll();

    UserDTO findById(Long id);

    void deleteById(Long id);

    User update(UpdateUserDTO dto);
}

