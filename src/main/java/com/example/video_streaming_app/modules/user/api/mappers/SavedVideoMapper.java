package com.example.video_streaming_app.modules.user.api.mappers;

import com.example.video_streaming_app.modules.user.api.dto.user.SavedVideoDto;
import com.example.video_streaming_app.modules.user.domain.SavedVideo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SavedVideoMapper {

    @Mapping(source = "id.user.id", target = "userId")
    @Mapping(source = "id.video.id", target = "videoId")
    SavedVideoDto toDto(SavedVideo savedVideo);

    List<SavedVideoDto> toDto(List<SavedVideo> savedVideos);

    @Mapping(source = "userId", target = "id.user.id")
    @Mapping(source = "videoId", target = "id.video.id")
    SavedVideo toEntity(SavedVideoDto savedVideoDto);

}