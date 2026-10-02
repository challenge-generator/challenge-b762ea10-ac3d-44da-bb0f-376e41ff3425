package com.pragma.comments.application.usecase;

import com.pragma.comments.application.validator.CommentValidator;
import com.pragma.comments.domain.exception.CommentNotFoundException;
import com.pragma.comments.domain.exception.CommentProcessingException;
import com.pragma.comments.domain.exception.InvalidCommentException;
import com.pragma.comments.domain.model.Comment;
import com.pragma.comments.domain.port.CommentRepository;
import com.pragma.comments.infrastructure.dto.CommentRequest;
import com.pragma.comments.infrastructure.dto.CommentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CommentUseCase.class);

    private final CommentRepository commentRepository;
    private final CommentValidator commentValidator;

    public CommentUseCase(CommentRepository commentRepository, CommentValidator commentValidator) {
        this.commentRepository = commentRepository;
        this.commentValidator = commentValidator;
    }

    @Transactional
    public CommentResponse createComment(CommentRequest request) {
        logger.info("Creating comment for postId: {} and userId: {}", request.getPostId(), request.getUserId());
        
        try {
            commentValidator.validateForCreation(request);
            
            Comment comment = Comment.create(
                request.getPostId(),
                request.getUserId(),
                request.getContent()
            );
            
            Comment savedComment = commentRepository.save(comment);
            logger.info("Comment created successfully with id: {}", savedComment.getId());
            
            return CommentResponse.fromDomain(savedComment);
            
        } catch (InvalidCommentException e) {
            logger.warn("Validation failed while creating comment: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error creating comment: {}", e.getMessage(), e);
            throw CommentProcessingException.forCreation(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public CommentResponse getCommentById(UUID id) {
        logger.info("Retrieving comment with id: {}", id);
        
        if (id == null) {
            throw InvalidCommentException.forNullField("id");
        }
        
        return commentRepository.findById(id)
            .map(CommentResponse::fromDomain)
            .orElseThrow(() -> {
                logger.warn("Comment not found with id: {}", id);
                return CommentNotFoundException.forId(id);
            });
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByPostId(UUID postId) {
        logger.info("Retrieving comments for postId: {}", postId);
        
        if (postId == null) {
            throw InvalidCommentException.forNullField("postId");
        }
        
        List<Comment> comments = commentRepository.findByPostId(postId);
        logger.info("Found {} comments for postId: {}", comments.size(), postId);
        
        return comments.stream()
            .map(CommentResponse::fromDomain)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByUserId(UUID userId) {
        logger.info("Retrieving comments for userId: {}", userId);
        
        if (userId == null) {
            throw InvalidCommentException.forNullField("userId");
        }
        
        List<Comment> comments = commentRepository.findByUserId(userId);
        logger.info("Found {} comments for userId: {}", comments.size(), userId);
        
        return comments.stream()
            .map(CommentResponse::fromDomain)
            .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponse updateComment(UUID id, String newContent) {
        logger.info("Updating comment with id: {}", id);
        
        try {
            commentValidator.validateContent(newContent);
            
            Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Comment not found for update with id: {}", id);
                    return CommentNotFoundException.forId(id);
                });
            
            Comment updatedComment = existingComment.withUpdatedContent(newContent);
            Comment savedComment = commentRepository.save(updatedComment);
            
            logger.info("Comment updated successfully with id: {}", savedComment.getId());
            return CommentResponse.fromDomain(savedComment);
            
        } catch (InvalidCommentException e) {
            logger.warn("Validation failed while updating comment: {}", e.getMessage());
            throw e;
        } catch (CommentNotFoundException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error updating comment: {}", e.getMessage(), e);
            throw CommentProcessingException.forUpdate(e.getMessage());
        }
    }

    @Transactional
    public void deleteComment(UUID id) {
        logger.info("Deleting comment with id: {}", id);
        
        if (id == null) {
            throw InvalidCommentException.forNullField("id");
        }
        
        if (!commentRepository.existsById(id)) {
            logger.warn("Comment not found for deletion with id: {}", id);
            throw CommentNotFoundException.forId(id);
        }
        
        try {
            commentRepository.deleteById(id);
            logger.info("Comment deleted successfully with id: {}", id);
        } catch (Exception e) {
            logger.error("Error deleting comment: {}", e.getMessage(), e);
            throw CommentProcessingException.forDeletion(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public boolean existsComment(UUID id) {
        if (id == null) {
            return false;
        }
        return commentRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public long countCommentsByPostId(UUID postId) {
        if (postId == null) {
            return 0;
        }
        return commentRepository.findByPostId(postId).size();
    }
}