package com.pdp.springm10.repository;

import com.pdp.springm10.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteByBlogId(Long blogId);
}