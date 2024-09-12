package com.pdp.springm10.service;

import com.pdp.springm10.entity.Comment;
import com.pdp.springm10.repository.CommentRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Aliabbos Ashurov
 * @since 12/September/2024  14:32
 **/
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public Optional<Comment> getComment(Long id) {
        return commentRepository.findById(id);
    }

    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
