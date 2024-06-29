package com.example.nangmanmemo.image.domain;

import com.example.nangmanmemo.post.domain.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "이미지 id", example = "1")
    @Column(name = "image_id")
    private Long imageId;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @Schema(description = "포스트 id", example = "1")
    private Post post;  // 게시글 ID를 참조하는 외래키

    @Builder
    public Image(String imageUrl, Post post) {
        this.imageUrl = imageUrl;
        this.post = post;
    }

}
