package com.pdp.springm10.dto;

import lombok.NonNull;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  16:27
 **/
public record RefreshTokenRequestDTO(
        @NonNull String refreshToken
) implements DTO {
}
