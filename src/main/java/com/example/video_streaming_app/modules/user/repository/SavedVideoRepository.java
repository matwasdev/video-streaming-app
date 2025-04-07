package com.example.video_streaming_app.modules.user.repository;

import com.example.video_streaming_app.modules.user.domain.SavedVideo;
import com.example.video_streaming_app.modules.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedVideoRepository extends JpaRepository<SavedVideo, Long> {

    List<SavedVideo> findByUser(User user);

}
