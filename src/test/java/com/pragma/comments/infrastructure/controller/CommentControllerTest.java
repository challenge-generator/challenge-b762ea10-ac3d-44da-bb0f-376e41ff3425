package com.pragma.comments.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.comments.application.usecase.CommentUseCase;
import com.pragma.comments.domain.exception.CommentNotFoundException;
import com.pragma.comments.domain.exception.InvalidCommentException;
import com.pragma.comments.domain.model.Comment;
import com.pragma.comments.infrastructure.dto.CommentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CommentUseCase commentUseCase;

    private UUID testPostId;
    private UUID testUserId;
    private String validContent;

    @BeforeEach
    void setUp() {
        testPostId = UUID.randomUUID();
        testUserId = UUID.randomUUID();
        validContent = "Contenido válido para prueba de comentario";
    }

    @Test
    void createComment_shouldReturn201WhenSuccessful() throws Exception {
        CommentRequest request = new CommentRequest(testPostId, testUserId, validContent);
        Comment createdComment = Comment.create(testPostId, testUserId, validContent);

        when(commentUseCase.createComment(any(UUID.class), any(UUID.class), any(String.class)))
            .thenReturn(createdComment);

        mockMvc.perform(post("/api/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.postId").value(testPostId.toString()))
            .andExpect(jsonPath("$.userId").value(testUserId.toString()))
            .andExpect(jsonPath("$.content").value(validContent));

        verify(commentUseCase, times(1)).createComment(any(UUID.class), any(UUID.class), any(String.class));
    }

    @Test
    void createComment_shouldReturn400ForInvalidContent() throws Exception {
        CommentRequest request = new CommentRequest(testPostId, testUserId, "");

        when(commentUseCase.createComment(any(UUID.class), any(UUID.class), any(String.class)))
            .thenThrow(InvalidCommentException.forEmptyContent());

        mockMvc.perform(post("/api/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getCommentById_shouldReturn200WhenFound() throws Exception {
        UUID commentId = UUID.randomUUID();
        Comment comment = Comment.create(testPostId, testUserId, validContent);

        when(commentUseCase.getCommentById(commentId)).thenReturn(comment);

        mockMvc.perform(get("/api/comments/{id}", commentId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(commentId.toString()))
            .andExpect(jsonPath("$.content").value(validContent));

        verify(commentUseCase, times(1)).getCommentById(commentId);
    }

    @Test
    void getCommentById_shouldReturn404WhenNotFound() throws Exception {
        UUID nonExistentId = UUID.randomUUID();

        when(commentUseCase.getCommentById(nonExistentId))
            .thenThrow(new CommentNotFoundException("Comentario no encontrado"));

        mockMvc.perform(get("/api/comments/{id}", nonExistentId))
            .andExpect(status().isNotFound());
    }

    @Test
    void getCommentsByPostId_shouldReturn200WithList() throws Exception {
        UUID postId = UUID.randomUUID();
        List<Comment> comments = List.of(
            Comment.create(postId, testUserId, "Comentario 1"),
            Comment.create(postId, testUserId, "Comentario 2")
        );

        when(commentUseCase.getCommentsByPostId(postId)).thenReturn(comments);

        mockMvc.perform(get("/api/comments/post/{postId}", postId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2));

        verify(commentUseCase, times(1)).getCommentsByPostId(postId);
    }

    @Test
    void getCommentsByUserId_shouldReturn200WithList() throws Exception {
        UUID userId = UUID.randomUUID();
        List<Comment> comments = List.of(
            Comment.create(testPostId, userId, "Comentario del usuario")
        );

        when(commentUseCase.getCommentsByUserId(userId)).thenReturn(comments);

        mockMvc.perform(get("/api/comments/user/{userId}", userId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(1))
            .andExpect(jsonPath("$[0].userId").value(userId.toString()));

        verify(commentUseCase, times(1)).getCommentsByUserId(userId);
    }

    @Test
    void updateComment_shouldReturn200WhenSuccessful() throws Exception {
        UUID commentId = UUID.randomUUID();
        String newContent = "Contenido actualizado correctamente";
        CommentRequest request = new CommentRequest(null, null, newContent);
        Comment updatedComment = Comment.create(testPostId, testUserId, newContent);

        when(commentUseCase.updateComment(eq(commentId), any(String.class))).thenReturn(updatedComment);

        mockMvc.perform(put("/api/comments/{id}", commentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content").value(newContent));

        verify(commentUseCase, times(1)).updateComment(eq(commentId), any(String.class));
    }

    @Test
    void updateComment_shouldReturn404WhenNotFound() throws Exception {
        UUID nonExistentId = UUID.randomUUID();
        CommentRequest request = new CommentRequest(null, null, "Nuevo contenido");

        when(commentUseCase.updateComment(eq(nonExistentId), any(String.class)))
            .thenThrow(new CommentNotFoundException("Comentario no encontrado"));

        mockMvc.perform(put("/api/comments/{id}", nonExistentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isNotFound());
    }

    @Test
    void deleteComment_shouldReturn204WhenSuccessful() throws Exception {
        UUID commentId = UUID.randomUUID();

        doNothing().when(commentUseCase).deleteComment(commentId);

        mockMvc.perform(delete("/api/comments/{id}", commentId))
            .andExpect(status().isNoContent());

        verify(commentUseCase, times(1)).deleteComment(commentId);
    }

    @Test
    void deleteComment_shouldReturn404WhenNotFound() throws Exception {
        UUID nonExistentId = UUID.randomUUID();

        doThrow(new CommentNotFoundException("Comentario no encontrado"))
            .when(commentUseCase).deleteComment(nonExistentId);

        mockMvc.perform(delete("/api/comments/{id}", nonExistentId))
            .andExpect(status().isNotFound());
    }

    @Test
    void createComment_shouldReturn400ForNullFields() throws Exception {
        CommentRequest request = new CommentRequest(null, null, null);

        when(commentUseCase.createComment(any(), any(), any()))
            .thenThrow(InvalidCommentException.forMultipleErrors(Map.of(
                "postId", "no puede ser nulo",
                "userId", "no puede ser nulo",
                "content", "no puede ser vacío"
            )));

        mockMvc.perform(post("/api/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }
}