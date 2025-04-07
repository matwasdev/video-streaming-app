package com.example.video_streaming_app.modules.user.service;

import com.example.video_streaming_app.modules.user.api.dto.request.saved_video.SavedVideoCreateRequest;
import com.example.video_streaming_app.modules.user.api.dto.user.SavedVideoDto;
import com.example.video_streaming_app.modules.user.api.mappers.SavedVideoMapper;
import com.example.video_streaming_app.modules.user.domain.SavedVideo;
import com.example.video_streaming_app.modules.user.domain.SavedVideoId;
import com.example.video_streaming_app.modules.user.domain.User;
import com.example.video_streaming_app.modules.user.repository.SavedVideoRepository;
import com.example.video_streaming_app.modules.user.repository.UserRepository;
import com.example.video_streaming_app.modules.video.domain.Video;
import com.example.video_streaming_app.modules.video.repository.VideoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SavedVideoService {

    private final SavedVideoRepository savedVideoRepository;
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;
    private final SavedVideoMapper savedVideoMapper;

    public SavedVideoDto saveVideo(SavedVideoCreateRequest request, Authentication authentication) {
        String loggedUsername = authentication.getName();
        Video video = videoRepository.findById(request.getVideoId())
                .orElseThrow( () -> new EntityNotFoundException("Video not found") );

        User user = userRepository.findById(request.getUserId())
                .orElseThrow( () -> new EntityNotFoundException("User not found") );

        if(!loggedUsername.equals(user.getUsername())) {
            throw new SecurityException("You are not logged in");
        }

        SavedVideoId savedVideoId = new SavedVideoId();
        savedVideoId.setUser(user);
        savedVideoId.setVideo(video);

        SavedVideo savedVideo = new SavedVideo();
        savedVideo.setId(savedVideoId);

        savedVideo = savedVideoRepository.save(savedVideo);
        return savedVideoMapper.toDto(savedVideo);
    }

    public List<SavedVideoDto> getAllUserSavedVideos(Long id, Authentication authentication) {
        String loggedUsername = authentication.getName();

        User user = userRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("User not found") );

        if(!loggedUsername.equals(user.getUsername())) {
            throw new SecurityException("You are not logged in");
        }

        List<SavedVideo> savedVideos = savedVideoRepository.findByUser(user);
        return savedVideos.stream().map(savedVideoMapper::toDto).collect(Collectors.toList());
    }

}
