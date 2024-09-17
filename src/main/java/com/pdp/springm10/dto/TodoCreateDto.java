package com.pdp.springm10.dto;

import com.pdp.springm10.enums.Category;
import com.pdp.springm10.enums.Level;

/**
 * @author Aliabbos Ashurov
 * @since 17/September/2024  14:44
 **/
public record TodoCreateDto(
        String title,
        String description,
        Category category,
        Level level,
        String deadLine,
        Integer userId
) {
}
