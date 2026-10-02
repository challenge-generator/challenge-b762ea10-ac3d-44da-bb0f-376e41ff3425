package com.pragma.comments.application.validator;

import com.pragma.comments.domain.exception.InvalidCommentException;
import com.pragma.comments.infrastructure.dto.CommentRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class CommentValidator {

    private static final int MIN_CONTENT_LENGTH = 1;
    private static final int MAX_CONTENT_LENGTH = 1000;
    private static final Pattern CONTENT_PATTERN = Pattern.compile("^[^<>\\x00-\\x1F]*$");
    private static final int MAX_POST_ID_LENGTH = 36;
    private static final int MAX_USER_ID_LENGTH = 36;

    public void validateForCreation(CommentRequest request) {
        Map<String, String> errors = new HashMap<>();
        
        validatePostId(request.getPostId(), errors);
        validateUserId(request.getUserId(), errors);
        validateContent(request.getContent(), errors);
        
        if (!errors.isEmpty()) {
            throw InvalidCommentException.forMultipleErrors(errors);
        }
    }

    public void validateContent(String content) {
        Map<String, String> errors = new HashMap<>();
        validateContent(content, errors);
        
        if (!errors.isEmpty()) {
            throw InvalidCommentException.forMultipleErrors(errors);
        }
    }

    private void validatePostId(java.util.UUID postId, Map<String, String> errors) {
        if (postId == null) {
            errors.put("postId", "El ID del post no puede ser nulo");
            return;
        }
        
        String postIdString = postId.toString();
        if (postIdString.isBlank()) {
            errors.put("postId", "El ID del post no puede estar vacío");
        } else if (postIdString.length() > MAX_POST_ID_LENGTH) {
            errors.put("postId", "El ID del post excede la longitud máxima permitida");
        }
    }

    private void validateUserId(java.util.UUID userId, Map<String, String> errors) {
        if (userId == null) {
            errors.put("userId", "El ID del usuario no puede ser nulo");
            return;
        }
        
        String userIdString = userId.toString();
        if (userIdString.isBlank()) {
            errors.put("userId", "El ID del usuario no puede estar vacío");
        } else if (userIdString.length() > MAX_USER_ID_LENGTH) {
            errors.put("userId", "El ID del usuario excede la longitud máxima permitida");
        }
    }

    private void validateContent(String content, Map<String, String> errors) {
        if (content == null) {
            errors.put("content", "El contenido del comentario no puede ser nulo");
            return;
        }
        
        String trimmedContent = content.trim();
        
        if (trimmedContent.isEmpty()) {
            errors.put("content", "El contenido del comentario no puede estar vacío");
            return;
        }
        
        if (trimmedContent.length() < MIN_CONTENT_LENGTH) {
            errors.put("content", "El contenido del comentario debe tener al menos " + MIN_CONTENT_LENGTH + " carácter(es)");
        }
        
        if (trimmedContent.length() > MAX_CONTENT_LENGTH) {
            errors.put("content", "El contenido del comentario excede el límite máximo de " + MAX_CONTENT_LENGTH + " caracteres");
        }
        
        if (!CONTENT_PATTERN.matcher(trimmedContent).matches()) {
            errors.put("content", "El contenido del comentario contiene caracteres no permitidos");
        }
        
        if (containsForbiddenPatterns(trimmedContent)) {
            errors.put("content", "El contenido del comentario contiene patrones forbidden");
        }
    }

    private boolean containsForbiddenPatterns(String content) {
        String lowerContent = content.toLowerCase();
        return lowerContent.contains("spam") || 
               lowerContent.contains("malicious") ||
               lowerContent.contains("<script>") ||
               lowerContent.contains("javascript:");
    }

    public boolean isValidContentLength(String content) {
        if (content == null) {
            return false;
        }
        int length = content.trim().length();
        return length >= MIN_CONTENT_LENGTH && length <= MAX_CONTENT_LENGTH;
    }

    public boolean isValidPostId(java.util.UUID postId) {
        return postId != null && !postId.toString().isBlank();
    }

    public boolean isValidUserId(java.util.UUID userId) {
        return userId != null && !userId.toString().isBlank();
    }

    public int getMaxContentLength() {
        return MAX_CONTENT_LENGTH;
    }

    public int getMinContentLength() {
        return MIN_CONTENT_LENGTH;
    }
}