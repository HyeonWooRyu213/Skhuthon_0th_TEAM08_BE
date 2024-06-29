package com.example.nangmanmemo.post.api.response;

import com.example.nangmanmemo.comment.domain.Comment;
import com.example.nangmanmemo.image.domain.Image;
import com.example.nangmanmemo.post.domain.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;

@Builder
public record PostInfoResDto(
        @Schema(description = "게시글 id", example = "1")
        Long postId,
        @NotBlank
        @Max(value = 20)
        @Schema(description = "게시글 제목", example = "이건 제목이야")
        String title,
        @Schema(description = "게시글 조회수", example = "3000")
        int view,
        @Schema(description = "게시글 좋아요 수", example = "104")
        int likes,
        @NotBlank
        @Schema(description = "게시글 내용", example = "이건 내용이야")
        String content
) {
    public static PostInfoResDto from (Post post) {
        return PostInfoResDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .view(post.getView())
                .likes(post.getLikes())
                .build();
    }
}
