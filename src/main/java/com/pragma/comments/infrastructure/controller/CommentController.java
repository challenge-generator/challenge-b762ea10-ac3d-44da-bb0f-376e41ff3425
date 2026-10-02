package com.pragma.comments.infrastructure.controller;

import com.pragma.comments.application.usecase.CommentUseCase;
import com.pragma.comments.domain.model.Comment;
import com.pragma.comments.infrastructure.dto.CommentRequest;
import com.pragma.comments.infrastructure.dto.CommentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comments")
@Tag(name = "Comments", description = "API para gestión de comentarios")
public class CommentController {

    private final CommentUseCase commentUseCase;

    public CommentController(CommentUseCase commentUseCase) {
        this.commentUseCase = commentUseCase;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo comentario", description = "Crea un nuevo comentario en el sistema")
    public ResponseEntity<CommentResponse> createComment(@Valid @RequestBody CommentRequest request) {
        Comment comment = commentUseCase.createComment(
                request.postId(),
                request.userId(),
                request.content()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(CommentResponse.fromDomain(comment));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un comentario por ID", description = "Retrieva un comentario específico por su identificador")
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable UUID id) {
        Comment comment = commentUseCase.getCommentById(id);
        return ResponseEntity.ok(CommentResponse.fromDomain(comment));
    }

    @GetMapping("/post/{postId}")
    @Operation(summary = "Obtener comentarios por post", description = "Retrieva todos los comentarios asociados a un post específico")
    public ResponseEntity<List<CommentResponse>> getCommentsByPostId(@PathVariable UUID postId) {
        List<Comment> comments = commentUseCase.getCommentsByPostId(postId);
        List<CommentResponse> responses = comments.stream()
                .map(CommentResponse::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Obtener comentarios por usuario", description = "Retrieva todos los comentarios creados por un usuario específico")
    public ResponseEntity<List<CommentResponse>> getCommentsByUserId(@PathVariable UUID userId) {
        List<Comment> comments = commentUseCase.getCommentsByUserId(userId);
        List<CommentResponse> responses = comments.stream()
                .map(CommentResponse::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un comentario", description = "Actualiza el contenido de un comentario existente")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable UUID id,
            @Valid @RequestBody CommentRequest request) {
        Comment comment = commentUseCase.updateComment(id, request.content());
        return ResponseEntity.ok(CommentResponse.fromDomain(comment));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un comentario", description = "Elimina un comentario del sistema por su identificador")
    public ResponseEntity<Void> deleteComment(@PathVariable UUID id) {
        commentUseCase.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}