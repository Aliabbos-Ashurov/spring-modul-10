package com.pdp.springm10.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  12:34
 **/
public record ErrorMessageDTO(
        String code,
        String message,
        String path,
        String details,
        LocalDateTime timestamp)
        implements DTO {

    public static ErrorMessageDTO of(String code, String message, String path) {
        return new ErrorMessageDTO(code, message, path, "Contact support for more details.",
                LocalDateTime.now(ZoneId.of("Asia/Tashkent")));
    }

    public String getTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timestamp.format(formatter);
    }
}