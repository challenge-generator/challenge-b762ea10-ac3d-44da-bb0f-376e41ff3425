package com.pragma.comments.infrastructure.dto;

import com.pragma.comments.domain.model.Comment;
import java.time.LocalDateTime;
import java.util.UUID;

public record CommentResponse(
    UUID id,
    UUID postId,
    UUID userId,
    String content,
    LocalDateTime createdAt
) {
    public static CommentResponse fromDomain(Comment comment) {
        return new CommentResponse(
            comment.getId(),
            comment.getPostId(),
            comment.getUserId(),
            comment.getContent(),
            comment.getCreatedAt()
        );
    }

    public static CommentResponse withoutSensitiveData(Comment comment) {
        return new CommentResponse(
            comment.getId(),
            comment.getPostId(),
            comment.getUserId(),
            maskContent(comment.getContent()),
            comment.getCreatedAt()
        );
    }

    private static String maskContent(String content) {
        if (content == null || content.length() <= 4) {
            return "****";
        }
        return content.substring(0, 2) + "**" + content.substring(content.length() - 2);
    }

    public boolean isValid() {
        return id != null && postId != null && userId != null 
            && content != null && !content.isBlank() && createdAt != null;
    }

    public String toDisplayString() {
        return String.format("Comment[id=%s, postId=%s, userId=%s, contentLength=%d, createdAt=%s]",
            id, postId, userId, content != null ? content.length() : 0, createdAt);
    }
}