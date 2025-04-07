package com.example.video_streaming_app.modules.video.controller;

import com.example.video_streaming_app.modules.video.api.dto.comment.CommentDto;
import com.example.video_streaming_app.modules.video.api.dto.request.comment.CommentCreateRequest;
import com.example.video_streaming_app.modules.video.api.dto.request.comment.CommentUpdateRequest;
import com.example.video_streaming_app.modules.video.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/add")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentCreateRequest request, Authentication authentication) {
        CommentDto commentDto = commentService.createComment(request,authentication);
        if (commentDto == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(commentDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Long id) {
        CommentDto commentDto = commentService.getComment(id);
        if (commentDto == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(commentDto);
    }

    @GetMapping("/get-for-vid/{id}")
    public ResponseEntity<List<CommentDto>> getCommentsForVideo(@PathVariable Long id) {
        List<CommentDto> commentDtos = commentService.getCommentsForVideo(id);
        if (commentDtos == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(commentDtos);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CommentDto>> getAllComments() {
        List<CommentDto> commentDtos = commentService.getAllComments();
        if (commentDtos == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(commentDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentUpdateRequest request, @PathVariable Long id, Authentication authentication) {
        CommentDto commentDto = commentService.updateComment(id, request,authentication);
        if (commentDto == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(commentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, Authentication authentication) {
        commentService.deleteComment(id, authentication);
        return ResponseEntity.noContent().build();
    }







}
