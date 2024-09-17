package com.pdp.springm10.dto;

import com.pdp.springm10.enums.Category;
import com.pdp.springm10.enums.Level;

/**
 * @author Aliabbos Ashurov
 * @since 17/September/2024  14:45
 **/
public record TodoUpdateDto(
        Integer id,
        String title,
        String description,
        Category category,
        Level level,
        String deadLine,
        Boolean completed
) {}

