package com.example.video_streaming_app.modules.user.controller;

import com.example.video_streaming_app.modules.user.api.dto.request.saved_video.SavedVideoCreateRequest;
import com.example.video_streaming_app.modules.user.api.dto.user.SavedVideoDto;
import com.example.video_streaming_app.modules.user.service.SavedVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saved-video/user")
@RequiredArgsConstructor
public class SavedVideoController {

    private final SavedVideoService savedVideoService;

    @PostMapping("/add")
    public ResponseEntity<SavedVideoDto> saveVideo(@RequestBody SavedVideoCreateRequest request, Authentication authentication) {
        SavedVideoDto savedVideoDto = savedVideoService.saveVideo(request, authentication);
        if(savedVideoDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(savedVideoDto);
    }



    @GetMapping("/{id}/all")
    public ResponseEntity<List<SavedVideoDto>> getSavedVideo(@PathVariable Long id, Authentication authentication) {
        List<SavedVideoDto> savedVideosDto = savedVideoService.getAllUserSavedVideos(id, authentication);
        if (savedVideosDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(savedVideosDto);
    }




}
