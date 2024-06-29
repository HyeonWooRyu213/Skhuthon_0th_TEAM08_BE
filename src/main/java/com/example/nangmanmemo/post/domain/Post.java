package com.example.nangmanmemo.post.domain;

import com.example.nangmanmemo.comment.domain.Comment;
import com.example.nangmanmemo.post.api.request.PostUpdateReqDto;
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
    private Long postId;

    // 게시글 제목
    @Column(name = "title")
    private String title;

    // 게시글 내용
    @Column(name = "content")
    private String content;

    // 게시글 조회수
    @Column(name = "view")
    private Integer view;

    // 게시글 좋아요 수
    @Column(name = "likes")
    private Integer likes = 0;

    // 게시글 신고
    @Column(name = "report")
    private String report;

    // 게시글 작성 시간
    @Column(name = "postDate")
    private LocalDateTime postDate;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    private Post(Long postId, String title, String content) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.postDate = LocalDateTime.now();
    }

    public void update(PostUpdateReqDto postUpdateReqDto) {
        this.title = postUpdateReqDto.title();
        this.content = postUpdateReqDto.content();
    }




}
