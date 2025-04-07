package com.example.video_streaming_app.modules.video.service;

import com.example.video_streaming_app.modules.video.api.dto.video.VideoDto;
import com.example.video_streaming_app.modules.video.api.mappers.VideoMapper;
import com.example.video_streaming_app.modules.video.domain.Video;
import com.example.video_streaming_app.modules.video.repository.VideoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;


    public List<VideoDto> getAllVideos() {
        List<Video> videos = videoRepository.findAll();
        return videoMapper.toDto(videos);
    }

}
