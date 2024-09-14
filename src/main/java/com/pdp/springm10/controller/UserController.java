package com.pdp.springm10.controller;

import com.pdp.springm10.dto.UpdateUserDTO;
import com.pdp.springm10.dto.UserDTO;
import com.pdp.springm10.entity.User;
import com.pdp.springm10.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  12:02
 **/
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    public ResponseEntity<Void> saveAllUsers(@RequestBody List<UserDTO> dtos) {
        userService.saveUsers(dtos);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserDTO dto) {
        return ResponseEntity.ok(userService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
