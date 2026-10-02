package com.pragma.comments.infrastructure.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record CommentRequest(
    @NotNull(message = "Post ID is required")
    UUID postId,
    
    @NotNull(message = "User ID is required")
    UUID userId,
    
    @NotNull(message = "Content is required")
    @Size(min = 1, max = 1000, message = "Content must be between 1 and 1000 characters")
    String content,
    
    @Size(max = 50, message = "Author name cannot exceed 50 characters")
    String authorName,
    
    String metadata
) {
    public CommentRequest {
        if (content != null) {
            content = content.trim();
        }
        if (authorName != null) {
            authorName = authorName.trim();
        }
    }

    public static CommentRequest create(UUID postId, UUID userId, String content) {
        return new CommentRequest(postId, userId, content, null, null);
    }

    public static CommentRequest createWithAuthor(UUID postId, UUID userId, String content, String authorName) {
        return new CommentRequest(postId, userId, content, authorName, null);
    }

    public boolean hasAuthor() {
        return authorName != null && !authorName.isBlank();
    }

    public boolean hasMetadata() {
        return metadata != null && !metadata.isBlank();
    }

    public String getContentPreview(int maxLength) {
        if (content == null) {
            return "";
        }
        return content.length() <= maxLength ? content : content.substring(0, maxLength) + "...";
    }
}