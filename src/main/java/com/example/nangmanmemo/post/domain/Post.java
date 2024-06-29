package com.example.nangmanmemo.post.domain;

import com.example.nangmanmemo.comment.domain.Comment;
import com.example.nangmanmemo.post.api.request.PostUpdateReqDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // 게시글 ID
    @Column(name = "post_id")
    @Schema(description = "게시글 id", example = "1")
    private Long postId;

    // 게시글 제목
    @Column(name = "title")
    @Schema(description = "게시글 제목", example = "이건 제목이야")
    private String title;

    // 게시글 내용
    @Column(name = "content")
    @Schema(description = "게시글 내용", example = "이건 내용이야")
    private String content;

    // 게시글 조회수
    @Column(name = "view", columnDefinition = "int default 0")
    @Schema(description = "게시글 조회수", example = "3000")
    private int view;

    // 게시글 좋아요 수
    @Column(name = "likes", columnDefinition = "int default 0")
    @Schema(description = "게시글 좋아요 수", example = "104")
    private int likes;

    // 게시글 작성 시간
    @Column(name = "postDate")
    @Schema(description = "게시글 작성시간", example = "2024-06-30T4:24")
    private LocalDateTime postDate;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    private Post(Long postId, String title, String content, int view, int likes) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.view = view;
        this.likes = likes;
        this.postDate = LocalDateTime.now();
    }

    public void update(PostUpdateReqDto postUpdateReqDto) {
        this.title = postUpdateReqDto.title();
        this.content = postUpdateReqDto.content();
    }

    // 조회수 증가 메서드
    public void incrementView() {
        this.view++;
    }

    // 좋아요 증가 메서드
    public void incrementLike() {
        this.likes++;
    }

}
