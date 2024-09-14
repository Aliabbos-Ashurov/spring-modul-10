package com.pdp.springm10.controller;

import com.pdp.springm10.dto.UpdateUserDTO;
import com.pdp.springm10.dto.UserDTO;
import com.pdp.springm10.entity.User;
import com.pdp.springm10.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Operation(summary = "Get all users", description = "Returns a list of all registered users. Requires authentication.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Operation(summary = "Find user by ID", description = "Returns a user based on their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Operation(summary = "Add multiple users", description = "Saves a list of new users in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Users added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user data")
    })
    @PostMapping("/add/users")
    public ResponseEntity<Void> saveAllUsers(@RequestBody List<UserDTO> dtos) {
        userService.saveUsers(dtos);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update user details", description = "Updates an existing user's information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Invalid user data"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserDTO dto) {
        return ResponseEntity.ok(userService.update(dto));
    }


    @Operation(summary = "Delete user by ID", description = "Deletes a user based on their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
