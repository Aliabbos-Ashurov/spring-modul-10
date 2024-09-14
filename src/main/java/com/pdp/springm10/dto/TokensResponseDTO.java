package com.pdp.springm10.dto;

import lombok.NonNull;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  16:25
 **/
public record TokensResponseDTO(@NonNull String accessToken,
                                @NonNull String refreshToken) implements DTO {
}
