package com.pdp.springm10.service;

import com.pdp.springm10.entity.Blog;
import com.pdp.springm10.event.BlogDeletedEvent;
import com.pdp.springm10.repository.BlogRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Aliabbos Ashurov
 * @since 12/September/2024  14:25
 **/
@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final ApplicationEventPublisher eventPublisher;

    public BlogService(BlogRepository blogRepository, ApplicationEventPublisher eventPublisher) {
        this.blogRepository = blogRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Transactional
    public void deleteBlog(Long id) {
        eventPublisher.publishEvent(new BlogDeletedEvent(id));
    }

    @Transactional(readOnly = true)
    public Optional<Blog> getBlog(Long id) {
        return blogRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }
}