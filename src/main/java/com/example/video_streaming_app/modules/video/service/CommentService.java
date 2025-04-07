package com.example.video_streaming_app.modules.video.service;

import com.example.video_streaming_app.modules.user.domain.User;
import com.example.video_streaming_app.modules.user.repository.UserRepository;
import com.example.video_streaming_app.modules.video.api.dto.comment.CommentDto;
import com.example.video_streaming_app.modules.video.api.dto.request.comment.CommentCreateRequest;
import com.example.video_streaming_app.modules.video.api.dto.request.comment.CommentUpdateRequest;
import com.example.video_streaming_app.modules.video.api.mappers.CommentMapper;
import com.example.video_streaming_app.modules.video.domain.Comment;
import com.example.video_streaming_app.modules.video.domain.Video;
import com.example.video_streaming_app.modules.video.repository.CommentRepository;
import com.example.video_streaming_app.modules.video.repository.VideoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;
    private final CommentMapper commentMapper;



    public CommentDto createComment(CommentCreateRequest request, Authentication authentication) {
        String loggedUsername = authentication.getName();
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Video video = videoRepository.findById(request.getVideoId())
                .orElseThrow(() -> new RuntimeException("Video not found"));

        if(!loggedUsername.equals(user.getUsername())) {
            throw new SecurityException("You are not logged in");
        }

        Comment comment = new Comment();
        comment.setComment(request.getComment());
        comment.setUser(user);
        comment.setVideo(video);

        comment = commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }


    public CommentDto getComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        return commentMapper.toDto(comment);
    }

    public List<CommentDto> getAllComments(){
        List<Comment> comments = commentRepository.findAll();
        return commentMapper.toDto(comments);
    }

    public List<CommentDto> getCommentsForVideo(Long videoId){
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new EntityNotFoundException("Video not found"));

        List<Comment> comments = commentRepository.findByVideoId(videoId);
        return commentMapper.toDto(comments);
    }


    public CommentDto updateComment(Long id, CommentUpdateRequest request, Authentication authentication) {
        String loggedUsername = authentication.getName();

        Comment comment = commentRepository.findById(request.getCommentId())
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        if(!loggedUsername.equals(comment.getUser().getUsername())) {
            throw new SecurityException("You are not logged in");
        }

        comment.setComment(request.getComment());
        comment = commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    public void deleteComment(Long id, Authentication authentication) {
        String loggedUsername = authentication.getName();

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        if(!loggedUsername.equals(comment.getUser().getUsername())) {
            throw new SecurityException("You are not logged in");
        }

        commentRepository.deleteById(id);
    }



}
