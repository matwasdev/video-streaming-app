package com.example.video_streaming_app.modules.video.controller;

import com.example.video_streaming_app.modules.video.api.dto.video.VideoDto;
import com.example.video_streaming_app.modules.video.domain.Video;
import com.example.video_streaming_app.modules.video.repository.VideoRepository;
import com.example.video_streaming_app.modules.video.service.VideoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/video")
@RequiredArgsConstructor
public class VideoController {

    private final VideoRepository videoRepository;
    private final VideoService videoService;

    @GetMapping("/{id}")
    public ResponseEntity<Resource> streamVideo(@RequestHeader(value = "Range", required = false) String rangeHeader, @PathVariable Long id) throws IOException {
        Video video = videoRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Video not found"));

        File videoFile = new File("app/videos/"+video.getVideoFilePath());
        long fileLength = videoFile.length();

        long rangeStart = 0;
        long rangeEnd = fileLength - 1;

        if (rangeHeader != null) {
            System.out.println(rangeHeader);
            String[] ranges = rangeHeader.substring(6).split("-");
            rangeStart = Long.parseLong(ranges[0]);
            if (ranges.length > 1) {
                rangeEnd = Long.parseLong(ranges[1]);
            }
        }


        long contentLength = rangeEnd - rangeStart + 1;

        InputStream inputStream = new FileInputStream(videoFile);
        inputStream.skip(rangeStart);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "video/mp4");
        headers.set("Accept-Ranges", "bytes");
        headers.set("Content-Length", String.valueOf(contentLength));
        headers.set("Content-Range", "bytes " + rangeStart + "-" + rangeEnd + "/" + fileLength);

        return ResponseEntity
                .status(rangeHeader != null ? HttpStatus.PARTIAL_CONTENT : HttpStatus.OK)
                .headers(headers)
                .body(new InputStreamResource(inputStream));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<VideoDto>> getAllVideos() {
        List<VideoDto> videoDtos = videoService.getAllVideos();
        if (videoDtos == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(videoDtos);
    }


}
