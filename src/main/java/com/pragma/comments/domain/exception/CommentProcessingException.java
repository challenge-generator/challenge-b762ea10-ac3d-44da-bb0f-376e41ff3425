package com.pragma.comments.domain.exception;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class CommentProcessingException extends RuntimeException {
    private final UUID commentId;
    private final String processingStage;
    private final ErrorType errorType;
    private final Map<String, String> context;
    private final LocalDateTime timestamp;

    public enum ErrorType {
        VALIDATION_ERROR,
        PERSISTENCE_ERROR,
        EXTERNAL_SERVICE_ERROR,
        INTERNAL_ERROR,
        TIMEOUT_ERROR,
        CONFLICT_ERROR
    }

    public CommentProcessingException(String message, ErrorType errorType) {
        super(message);
        this.commentId = null;
        this.processingStage = "UNKNOWN";
        this.errorType = errorType;
        this.context = Map.of("errorType", errorType.name());
        this.timestamp = LocalDateTime.now();
    }

    public CommentProcessingException(UUID commentId, String message, ErrorType errorType) {
        super(message);
        this.commentId = commentId;
        this.processingStage = "UNKNOWN";
        this.errorType = errorType;
        this.context = Map.of(
            "commentId", commentId.toString(),
            "errorType", errorType.name()
        );
        this.timestamp = LocalDateTime.now();
    }

    public CommentProcessingException(UUID commentId, String processingStage, String message, ErrorType errorType) {
        super(message);
        this.commentId = commentId;
        this.processingStage = processingStage;
        this.errorType = errorType;
        this.context = Map.of(
            "commentId", commentId.toString(),
            "processingStage", processingStage,
            "errorType", errorType.name()
        );
        this.timestamp = LocalDateTime.now();
    }

    public CommentProcessingException(UUID commentId, String processingStage, String message, ErrorType errorType, Throwable cause) {
        super(message, cause);
        this.commentId = commentId;
        this.processingStage = processingStage;
        this.errorType = errorType;
        this.context = Map.of(
            "commentId", commentId.toString(),
            "processingStage", processingStage,
            "errorType", errorType.name(),
            "cause", cause.getClass().getSimpleName()
        );
        this.timestamp = LocalDateTime.now();
    }

    public UUID getCommentId() {
        return commentId;
    }

    public String getProcessingStage() {
        return processingStage;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public Map<String, String> getContext() {
        return context;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean isRetryable() {
        return errorType == ErrorType.EXTERNAL_SERVICE_ERROR || 
               errorType == ErrorType.TIMEOUT_ERROR ||
               errorType == ErrorType.PERSISTENCE_ERROR;
    }

    public static CommentProcessingException validationError(String message) {
        return new CommentProcessingException(null, message, ErrorType.VALIDATION_ERROR);
    }

    public static CommentProcessingException persistenceError(UUID commentId, Throwable cause) {
        return new CommentProcessingException(commentId, "PERSISTENCE", "Failed to persist comment", ErrorType.PERSISTENCE_ERROR, cause);
    }

    public static CommentProcessingException externalServiceError(UUID commentId, String serviceName, Throwable cause) {
        return new CommentProcessingException(commentId, "EXTERNAL_SERVICE_" + serviceName, 
            "External service error during comment processing", ErrorType.EXTERNAL_SERVICE_ERROR, cause);
    }
}