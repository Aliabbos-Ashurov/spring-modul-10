package com.pdp.springm10.dto;

/**
 * @author Aliabbos Ashurov
 * @since 17/September/2024  14:45
 **/
public record UserUpdateDto(
        Integer id,
        String fullName,
        String email,
        String password
) {}
