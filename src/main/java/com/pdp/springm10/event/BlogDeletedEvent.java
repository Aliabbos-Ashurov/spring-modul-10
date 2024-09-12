package com.pdp.springm10.event;

import lombok.Getter;

/**
 * @author Aliabbos Ashurov
 * @since 12/September/2024  14:26
 **/
@Getter
public class BlogDeletedEvent {
    private final Long blogId;

    public BlogDeletedEvent(Long blogId) {
        this.blogId = blogId;
    }
}
