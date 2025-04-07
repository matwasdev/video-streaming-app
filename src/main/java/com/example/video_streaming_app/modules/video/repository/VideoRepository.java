package com.example.video_streaming_app.modules.video.repository;

import com.example.video_streaming_app.modules.video.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}
