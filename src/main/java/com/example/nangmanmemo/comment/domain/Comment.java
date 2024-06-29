package com.example.nangmanmemo.comment.domain;

import com.example.nangmanmemo.comment.api.request.CommentUpdateReqDto;
import com.example.nangmanmemo.post.domain.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // 댓글 ID
    @Column(name = "comment_id")
    private Long commentId;

    // 댓글 내용
    @Column(name = "content")
    private String content;

    // 댓글 추천수
    @Column(name = "likes")
    private String likes;

    // 댓글 신고
    @Column(name = "report")
    private String report;

    // 댓글 작성 시간
    @Column(name = "commentDate")
    private LocalDateTime commentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    private Comment(Long commentId, String content, Post post) {
        this.commentId = commentId;
        this.content = content;
        this.post = post;
        this.commentDate = LocalDateTime.now();
    }

    public void update(CommentUpdateReqDto commentUpdateReqDto) {
        this.content = commentUpdateReqDto.content();
    }
}
