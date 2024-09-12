package com.pdp.springm10.listener;

import com.pdp.springm10.event.BlogDeletedEvent;
import com.pdp.springm10.repository.BlogRepository;
import com.pdp.springm10.repository.CommentRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Aliabbos Ashurov
 * @since 12/September/2024  14:28
 **/
@Component
public class BlogDeletedEventHandler {
    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;

    public BlogDeletedEventHandler(BlogRepository blogRepository, CommentRepository commentRepository) {
        this.blogRepository = blogRepository;
        this.commentRepository = commentRepository;
    }

    @EventListener
    public void handleBlogDeletedEvent(BlogDeletedEvent event) {
        Long blogId = event.getBlogId();
        commentRepository.deleteByBlogId(blogId);
        blogRepository.deleteById(blogId);
    }
}
