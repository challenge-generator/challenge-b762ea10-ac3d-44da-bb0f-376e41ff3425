package com.pragma.comments.infrastructure.adapter;

import com.pragma.comments.domain.model.Comment;
import com.pragma.comments.domain.port.CommentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CommentJpaAdapter implements CommentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            entityManager.persist(comment);
            return comment;
        } else {
            return entityManager.merge(comment);
        }
    }

    @Override
    public Optional<Comment> findById(UUID id) {
        Comment comment = entityManager.find(Comment.class, id);
        return Optional.ofNullable(comment);
    }

    @Override
    public List<Comment> findByPostId(UUID postId) {
        return entityManager.createQuery(
                "SELECT c FROM Comment c WHERE c.postId = :postId ORDER BY c.createdAt DESC",
                Comment.class)
                .setParameter("postId", postId)
                .getResultList();
    }

    @Override
    public List<Comment> findByUserId(UUID userId) {
        return entityManager.createQuery(
                "SELECT c FROM Comment c WHERE c.userId = :userId ORDER BY c.createdAt DESC",
                Comment.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public void deleteById(UUID id) {
        Comment comment = entityManager.find(Comment.class, id);
        if (comment != null) {
            entityManager.remove(comment);
        }
    }

    @Override
    public boolean existsById(UUID id) {
        Comment comment = entityManager.find(Comment.class, id);
        return comment != null;
    }
}