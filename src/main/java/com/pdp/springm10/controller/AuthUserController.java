package com.pdp.springm10.controller;

import com.pdp.springm10.dto.AuthUserCreateDTO;
import com.pdp.springm10.dto.AuthUserCriteriaDTO;
import com.pdp.springm10.dto.AuthUserGetDTO;
import com.pdp.springm10.dto.AuthUserUpdateDTO;
import com.pdp.springm10.service.AuthUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 15/September/2024  14:42
 **/
@RestController
@RequestMapping("/api/authuser")
public class AuthUserController {

    private final AuthUserService service;

    public AuthUserController(AuthUserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody AuthUserCreateDTO dto) {
        service.create(dto);
        return new ResponseEntity<>("Successfully Created - User", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> update(@Valid @RequestBody AuthUserUpdateDTO dto) {
        service.update(dto);
        return new ResponseEntity<>("Successfully Updated - User", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>("Successfully Deleted - User", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthUserGetDTO> get(@PathVariable String id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AuthUserGetDTO>> list(@Valid AuthUserCriteriaDTO criteria) {
        return new ResponseEntity<>(service.list(criteria), HttpStatus.OK);
    }
}
