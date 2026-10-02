package com.pragma.comments.domain.exception;


import com.pragma.comments.domain.model.Comment;
import java.util.Map;
import java.util.UUID;

public class CommentNotFoundException extends RuntimeException {
    private final UUID commentId;
    private final Map<String, String> additionalInfo;

    public CommentNotFoundException(UUID commentId) {
        super("Comment not found with id: " + commentId);
        this.commentId = commentId;
        this.additionalInfo = Map.of("commentId", commentId.toString(), "error", "NOT_FOUND");
    }

    public CommentNotFoundException(UUID commentId, String message) {
        super(message);
        this.commentId = commentId;
        this.additionalInfo = Map.of("commentId", commentId.toString(), "error", "NOT_FOUND");
    }

    public CommentNotFoundException(UUID commentId, Throwable cause) {
        super("Comment not found with id: " + commentId, cause);
        this.commentId = commentId;
        this.additionalInfo = Map.of("commentId", commentId.toString(), "error", "NOT_FOUND");
    }

    public UUID getCommentId() {
        return commentId;
    }

    public Map<String, String> getAdditionalInfo() {
        return additionalInfo;
    }

    public static CommentNotFoundException forComment(UUID commentId) {
        return new CommentNotFoundException(commentId);
    }

    public static CommentNotFoundException forCommentWithMessage(UUID commentId, String message) {
        return new CommentNotFoundException(commentId, message);
    }
}