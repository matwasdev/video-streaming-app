package com.example.video_streaming_app.modules.video.api.mappers;

import com.example.video_streaming_app.modules.video.api.dto.video.VideoDto;
import com.example.video_streaming_app.modules.video.domain.Video;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VideoMapper {

    VideoDto toDto(Video video);
    List<VideoDto> toDto(List<Video> videos);
    Video toEntity(VideoDto videoDto);

}