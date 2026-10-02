package com.pragma.comments.domain.exception;

import java.util.Map;
import java.util.Collections;
import java.util.HashMap;

public class InvalidCommentException extends RuntimeException {
    private final Map<String, String> errors;

    public InvalidCommentException(String message) {
        super(message);
        this.errors = Collections.emptyMap();
    }

    public InvalidCommentException(String message, Map<String, String> errors) {
        super(message);
        this.errors = new HashMap<>(errors);
    }

    public Map<String, String> getErrors() {
        return Collections.unmodifiableMap(errors);
    }

    public static InvalidCommentException forEmptyContent() {
        Map<String, String> errors = new HashMap<>();
        errors.put("content", "El contenido del comentario no puede estar vacío");
        return new InvalidCommentException("Validación fallida para el comentario", errors);
    }

    public static InvalidCommentException forMaxLengthExceeded() {
        Map<String, String> errors = new HashMap<>();
        errors.put("content", "El contenido del comentario no puede exceder los 500 caracteres");
        return new InvalidCommentException("Validación fallida para el comentario", errors);
    }

    public static InvalidCommentException forNullField(String fieldName) {
        Map<String, String> errors = new HashMap<>();
        errors.put(fieldName, "El campo " + fieldName + " no puede ser nulo");
        return new InvalidCommentException("Validación fallida para el comentario", errors);
    }

    public static InvalidCommentException forMultipleErrors(Map<String, String> errors) {
        return new InvalidCommentException("Validación fallida para el comentario", errors);
    }
}