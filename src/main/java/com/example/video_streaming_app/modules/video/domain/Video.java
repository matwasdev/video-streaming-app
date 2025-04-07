package com.example.video_streaming_app.modules.video.domain;

import com.example.video_streaming_app.modules.user.domain.SavedVideo;
import com.example.video_streaming_app.modules.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, unique = true, name = "video_file_path")
    private String videoFilePath;

    @Column(nullable = false, updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "video",fetch = FetchType.LAZY)
    private Set<SavedVideo> savedByUsers;

    @OneToMany(mappedBy = "video",fetch = FetchType.LAZY)
    private Set<Comment> comments;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }


}
