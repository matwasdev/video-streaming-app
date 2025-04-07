package com.example.video_streaming_app.modules.user.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavedVideoDto {

    private Long userId;
    private Long videoId;


}
