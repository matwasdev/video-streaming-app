package com.example.video_streaming_app.modules.user.domain;

import com.example.video_streaming_app.modules.video.domain.Video;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "saved_videos")
public class SavedVideo {

    @EmbeddedId
    private SavedVideoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    private Video video;



    @Column(nullable = false, name = "saved_at")
    private LocalDateTime savedAt;


    @PrePersist
    public void prePersist() {
        this.savedAt = LocalDateTime.now();
    }



}
