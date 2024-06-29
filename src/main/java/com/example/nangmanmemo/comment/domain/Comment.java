package com.example.nangmanmemo.comment.domain;

import com.example.nangmanmemo.comment.api.request.CommentUpdateReqDto;
import com.example.nangmanmemo.post.domain.Post;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "댓글 id", example = "1")
    @Column(name = "comment_id")
    private Long commentId;

    // 댓글 내용
    @Schema(description = "댓글 내용", example = "정말 멋져요")
    @Column(name = "content")
    private String content;

    // 댓글 작성 시간
    @Schema(description = "댓글 작성 시간")
    @Column(name = "commentDate")
    private LocalDateTime commentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @Schema(description = "게시물 id", example = "1")
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
