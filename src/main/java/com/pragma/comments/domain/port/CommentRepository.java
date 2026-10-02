package com.pragma.comments.domain.port;

import com.pragma.comments.domain.model.Comment;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(UUID id);
    List<Comment> findByPostId(UUID postId);
    List<Comment> findByUserId(UUID userId);
    void deleteById(UUID id);
    boolean existsById(UUID id);
}