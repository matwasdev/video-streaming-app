package com.example.video_streaming_app.modules.video.api.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private String comment;
    private Long videoId;
    private Long userId;
    private LocalDateTime createdAt;

}
