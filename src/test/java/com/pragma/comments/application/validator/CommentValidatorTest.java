package com.pragma.comments.application.validator;

import com.pragma.comments.domain.exception.InvalidCommentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CommentValidatorTest {

    @InjectMocks
    private CommentValidator commentValidator;

    private UUID validPostId;
    private UUID validUserId;
    private String validContent;

    @BeforeEach
    void setUp() {
        validPostId = UUID.randomUUID();
        validUserId = UUID.randomUUID();
        validContent = "Contenido válido para el comentario de prueba";
    }

    @Test
    void validate_shouldNotThrowExceptionForValidData() {
        assertDoesNotThrow(() ->
            commentValidator.validate(validPostId, validUserId, validContent));
    }

    @Test
    void validate_shouldThrowExceptionForNullPostId() {
        InvalidCommentException exception = assertThrows(
            InvalidCommentException.class,
            () -> commentValidator.validate(null, validUserId, validContent)
        );

        Map<String, String> errors = exception.getErrors();
        assertTrue(errors.containsKey("postId"));
        assertEquals("no puede ser nulo", errors.get("postId"));
    }

    @Test
    void validate_shouldThrowExceptionForNullUserId() {
        InvalidCommentException exception = assertThrows(
            InvalidCommentException.class,
            () -> commentValidator.validate(validPostId, null, validContent)
        );

        Map<String, String> errors = exception.getErrors();
        assertTrue(errors.containsKey("userId"));
        assertEquals("no puede ser nulo", errors.get("userId"));
    }

    @Test
    void validate_shouldThrowExceptionForEmptyContent() {
        InvalidCommentException exception = assertThrows(
            InvalidCommentException.class,
            () -> commentValidator.validate(validPostId, validUserId, "")
        );

        Map<String, String> errors = exception.getErrors();
        assertTrue(errors.containsKey("content"));
        assertTrue(errors.get("content").contains("vacío") ||
                    errors.get("content").contains("empty"));
    }

    @Test
    void validate_shouldThrowExceptionForNullContent() {
        InvalidCommentException exception = assertThrows(
            InvalidCommentException.class,
            () -> commentValidator.validate(validPostId, validUserId, null)
        );

        Map<String, String> errors = exception.getErrors();
        assertTrue(errors.containsKey("content"));
    }

    @Test
    void validate_shouldThrowExceptionForContentExceedingMaxLength() {
        String longContent = "a".repeat(501);

        InvalidCommentException exception = assertThrows(
            InvalidCommentException.class,
            () -> commentValidator.validate(validPostId, validUserId, longContent)
        );

        Map<String, String> errors = exception.getErrors();
        assertTrue(errors.containsKey("content"));
        assertTrue(errors.get("content").contains("500") ||
                    errors.get("content").contains("máximo") ||
                    errors.get("content").contains("max"));
    }

    @Test
    void validate_shouldCollectMultipleErrors() {
        InvalidCommentException exception = assertThrows(
            InvalidCommentException.class,
            () -> commentValidator.validate(null, null, "")
        );

        Map<String, String> errors = exception.getErrors();
        assertTrue(errors.containsKey("postId"));
        assertTrue(errors.containsKey("userId"));
        assertTrue(errors.containsKey("content"));
    }

    @Test
    void validateContent_shouldPassForValidContent() {
        assertDoesNotThrow(() -> commentValidator.validateContent(validContent));
    }

    @Test
    void validateContent_shouldPassForContentAtMaxLength() {
        String maxContent = "a".repeat(500);
        assertDoesNotThrow(() -> commentValidator.validateContent(maxContent));
    }

    @Test
    void validateContent_shouldPassForSingleCharacter() {
        assertDoesNotThrow(() -> commentValidator.validateContent("a"));
    }

    @Test
    void validateRequiredField_shouldThrowForNullValue() {
        InvalidCommentException exception = assertThrows(
            InvalidCommentException.class,
            () -> commentValidator.validateRequiredField(null, "testField")
        );

        assertEquals("testField no puede ser nulo", exception.getMessage());
    }

    @Test
    void validateRequiredField_shouldNotThrowForNonNullValue() {
        assertDoesNotThrow(() ->
            commentValidator.validateRequiredField("valor", "testField"));
    }

    @Test
    void validateContentLength_shouldPassForValidLength() {
        assertDoesNotThrow(() ->
            commentValidator.validateContentLength(validContent));
    }

    @Test
    void validateContentLength_shouldThrowForContentTooLong() {
        String tooLongContent = "x".repeat(1000);

        InvalidCommentException exception = assertThrows(
            InvalidCommentException.class,
            () -> commentValidator.validateContentLength(tooLongContent)
        );

        assertNotNull(exception.getErrors().get("content"));
    }

    @Test
    void validateContentLength_shouldThrowForEmptyContent() {
        InvalidCommentException exception = assertThrows(
            InvalidCommentException.class,
            () -> commentValidator.validateContentLength("")
        );

        assertNotNull(exception.getErrors().get("content"));
    }

    @Test
    void validateContentLength_shouldThrowForBlankContent() {
        InvalidCommentException exception = assertThrows(
            InvalidCommentException.class,
            () -> commentValidator.validateContentLength("   ")
        );

        assertNotNull(exception.getErrors().get("content"));
    }
}