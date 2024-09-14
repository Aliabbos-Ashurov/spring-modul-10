package com.pdp.springm10.dto;

import lombok.NonNull;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  12:10
 **/
public record UserDTO(@NonNull String fullname,
                      @NonNull String username,
                      @NonNull String password) implements DTO {
}
