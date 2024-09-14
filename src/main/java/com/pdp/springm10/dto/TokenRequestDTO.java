package com.pdp.springm10.dto;

import lombok.NonNull;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  14:05
 **/
public record TokenRequestDTO(@NonNull String username,
                              @NonNull String password) implements DTO {
}
