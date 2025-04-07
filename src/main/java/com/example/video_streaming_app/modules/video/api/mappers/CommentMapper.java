package com.example.video_streaming_app.modules.video.api.mappers;

import com.example.video_streaming_app.modules.video.api.dto.comment.CommentDto;
import com.example.video_streaming_app.modules.video.domain.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    @Mapping(source = "video.id", target = "videoId")
    @Mapping(source = "user.id", target = "userId")
    CommentDto toDto(Comment comment);

    List<CommentDto> toDto(List<Comment> comments);

    @Mapping(target = "video", ignore = true)
    @Mapping(target = "user", ignore = true)
    Comment toEntity(CommentDto commentDto);

}