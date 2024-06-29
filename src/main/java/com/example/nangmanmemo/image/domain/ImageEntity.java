package com.example.nangmanmemo.image.domain;

import com.example.nangmanmemo.post.domain.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;  // 게시글 ID를 참조하는 외래키

    @Builder
    private ImageEntity(String imageUrl, Post post) {
        this.imageUrl = imageUrl;
        this.post = post;
    }

    public void updateImage(String newImageUrl) {
        this.imageUrl = newImageUrl;
    }
}
