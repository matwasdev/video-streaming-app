package com.example.video_streaming_app.modules.user.api.dto.request.saved_video;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavedVideoCreateRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long videoId;

}