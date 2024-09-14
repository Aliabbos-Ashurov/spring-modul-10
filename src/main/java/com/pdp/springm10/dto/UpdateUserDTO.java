package com.pdp.springm10.dto;

import lombok.NonNull;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  12:16
 **/
public record UpdateUserDTO(@NonNull Long id,
                            @NonNull String fullname,
                            @NonNull String username,
                            @NonNull String password) implements DTO {
}
