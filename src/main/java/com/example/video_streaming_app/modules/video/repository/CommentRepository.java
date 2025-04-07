package com.example.video_streaming_app.modules.video.repository;

import com.example.video_streaming_app.modules.video.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

    List<Comment> findByVideoId(Long videoId);

}
