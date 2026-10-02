package com.pragma.comments.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

public class Comment {
    private final UUID id;
    private final UUID postId;
    private final UUID userId;

    @NotBlank(message = "El contenido del comentario no puede estar vacío")
    @Size(max = 500, message = "El contenido del comentario no puede exceder los 500 caracteres")
    private final String content;

    @NotNull(message = "La fecha de creación no puede ser nula")
    private final LocalDateTime createdAt;

    private Comment(UUID id, UUID postId, UUID userId, String content, LocalDateTime createdAt) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
        validateInvariants();
    }

    public static Comment create(UUID postId, UUID userId, String content) {
        return new Comment(
                UUID.randomUUID(),
                postId,
                userId,
                content,
                LocalDateTime.now()
        );
    }

    public Comment withUpdatedContent(String newContent) {
        if (newContent == null || newContent.trim().isEmpty()) {
            throw new IllegalArgumentException("El nuevo contenido no puede ser vacío");
        }
        if (newContent.length() > 500) {
            throw new IllegalArgumentException("El nuevo contenido no puede exceder los 500 caracteres");
        }
        return new Comment(this.id, this.postId, this.userId, newContent, this.createdAt);
    }

    private void validateInvariants() {
        if (postId == null) {
            throw new IllegalArgumentException("El postId no puede ser nulo");
        }
        if (userId == null) {
            throw new IllegalArgumentException("El userId no puede ser nulo");
        }
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("El contenido no puede estar vacío");
        }
        if (content.length() > 500) {
            throw new IllegalArgumentException("El contenido no puede exceder los 500 caracteres");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("La fecha de creación no puede ser nula");
        }
    }

    public UUID getId() {
        return id;
    }

    public UUID getPostId() {
        return postId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}