package com.example.video_streaming_app.modules.user.api.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    @NotNull
    private Long userId;

    @NotBlank
    @Size(max=30)
    private String username;

    @Size(max = 100)
    private String password;

}
