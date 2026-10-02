package com.pragma.comments.application.usecase;

import com.pragma.comments.domain.exception.CommentNotFoundException;
import com.pragma.comments.domain.exception.InvalidCommentException;
import com.pragma.comments.domain.model.Comment;
import com.pragma.comments.domain.port.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentUseCaseTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentUseCase commentUseCase;

    private UUID testPostId;
    private UUID testUserId;
    private String validContent;

    @BeforeEach
    void setUp() {
        testPostId = UUID.randomUUID();
        testUserId = UUID.randomUUID();
        validContent = "Este es un comentario válido para testing";
    }

    @Test
    void createComment_shouldReturnCreatedComment() {
        when(commentRepository.save(any(Comment.class))).thenAnswer(invocation -> {
            Comment comment = invocation.getArgument(0);
            return Comment.create(comment.getPostId(), comment.getUserId(), comment.getContent());
        });

        Comment result = commentUseCase.createComment(testPostId, testUserId, validContent);

        assertNotNull(result);
        assertEquals(testPostId, result.getPostId());
        assertEquals(testUserId, result.getUserId());
        assertEquals(validContent, result.getContent());
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void createComment_shouldThrowExceptionForEmptyContent() {
        when(commentRepository.save(any(Comment.class))).thenThrow(InvalidCommentException.forEmptyContent());

        assertThrows(InvalidCommentException.class, () ->
            commentUseCase.createComment(testPostId, testUserId, ""));
    }

    @Test
    void createComment_shouldThrowExceptionForNullContent() {
        assertThrows(InvalidCommentException.class, () ->
            commentUseCase.createComment(testPostId, testUserId, null));
    }

    @Test
    void getCommentById_shouldReturnCommentWhenExists() {
        UUID commentId = UUID.randomUUID();
        Comment existingComment = Comment.create(testPostId, testUserId, validContent);

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));

        Comment result = commentUseCase.getCommentById(commentId);

        assertNotNull(result);
        assertEquals(existingComment.getId(), result.getId());
        verify(commentRepository, times(1)).findById(commentId);
    }

    @Test
    void getCommentById_shouldThrowExceptionWhenNotFound() {
        UUID nonExistentId = UUID.randomUUID();
        when(commentRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        assertThrows(CommentNotFoundException.class, () ->
            commentUseCase.getCommentById(nonExistentId));
    }

    @Test
    void getCommentsByPostId_shouldReturnListOfComments() {
        UUID postId = UUID.randomUUID();
        List<Comment> comments = List.of(
            Comment.create(postId, testUserId, "Comentario 1"),
            Comment.create(postId, testUserId, "Comentario 2"),
            Comment.create(postId, testUserId, "Comentario 3")
        );

        when(commentRepository.findByPostId(postId)).thenReturn(comments);

        List<Comment> result = commentUseCase.getCommentsByPostId(postId);

        assertEquals(3, result.size());
        assertTrue(result.stream().allMatch(c -> c.getPostId().equals(postId)));
        verify(commentRepository, times(1)).findByPostId(postId);
    }

    @Test
    void getCommentsByPostId_shouldReturnEmptyListWhenNoComments() {
        UUID postIdWithoutComments = UUID.randomUUID();
        when(commentRepository.findByPostId(postIdWithoutComments)).thenReturn(Collections.emptyList());

        List<Comment> result = commentUseCase.getCommentsByPostId(postIdWithoutComments);

        assertTrue(result.isEmpty());
        verify(commentRepository, times(1)).findByPostId(postIdWithoutComments);
    }

    @Test
    void getCommentsByUserId_shouldReturnListOfComments() {
        UUID userId = UUID.randomUUID();
        List<Comment> comments = List.of(
            Comment.create(testPostId, userId, "Comentario del usuario 1"),
            Comment.create(testPostId, userId, "Comentario del usuario 2")
        );

        when(commentRepository.findByUserId(userId)).thenReturn(comments);

        List<Comment> result = commentUseCase.getCommentsByUserId(userId);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(c -> c.getUserId().equals(userId)));
        verify(commentRepository, times(1)).findByUserId(userId);
    }

    @Test
    void updateComment_shouldUpdateAndReturnComment() {
        UUID commentId = UUID.randomUUID();
        String newContent = "Contenido actualizado";
        Comment existingComment = Comment.create(testPostId, testUserId, validContent);

        when(commentRepository.existsById(commentId)).thenReturn(true);
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));
        when(commentRepository.save(any(Comment.class))).thenAnswer(inv -> inv.getArgument(0));

        Comment result = commentUseCase.updateComment(commentId, newContent);

        assertNotNull(result);
        assertEquals(newContent, result.getContent());
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void updateComment_shouldThrowExceptionWhenNotFound() {
        UUID nonExistentId = UUID.randomUUID();
        when(commentRepository.existsById(nonExistentId)).thenReturn(false);

        assertThrows(CommentNotFoundException.class, () ->
            commentUseCase.updateComment(nonExistentId, "Nuevo contenido"));
    }

    @Test
    void deleteComment_shouldCallRepositoryDelete() {
        UUID commentId = UUID.randomUUID();
        when(commentRepository.existsById(commentId)).thenReturn(true);

        commentUseCase.deleteComment(commentId);

        verify(commentRepository, times(1)).deleteById(commentId);
    }

    @Test
    void deleteComment_shouldThrowExceptionWhenNotFound() {
        UUID nonExistentId = UUID.randomUUID();
        when(commentRepository.existsById(nonExistentId)).thenReturn(false);

        assertThrows(CommentNotFoundException.class, () ->
            commentUseCase.deleteComment(nonExistentId));
    }
}