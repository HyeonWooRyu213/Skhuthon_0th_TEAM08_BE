package com.example.nangmanmemo.post.api.response;

import com.example.nangmanmemo.comment.domain.Comment;
import com.example.nangmanmemo.image.domain.Image;
import com.example.nangmanmemo.post.domain.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record DetailPostResDto(
        @Schema(description = "게시글 id", example = "1")
        Long postId,
        @NotBlank
        @Schema(description = "게시글 제목", example = "나는 제목")
        @Max(value = 20)
        String title,
        @NotBlank
        @Schema(description = "게시글 내용", example = "나는 내용")
        String content,
        @NotBlank
        @Schema(description = "이미지 주소", example = "https://sfsfsjl.png")
        String imageUrl,
        @NotBlank
        @Schema(description = "게시글 좋아요 수", example = "104")
        int likes,
        @NotBlank
        @Schema(description = "게시글 조회수", example = "3000")
        int view,
        @Schema(description = "게시글 작성시간", example = "2024-06-30T4:32")
        LocalDateTime postDate,
        @NotNull
        @Schema(description = "댓글", example = "commentId:24, content:댓글내용")
        List<CommentDto> comments
) {
    public static DetailPostResDto from(Post post, List<Comment> comments, Image image) {
        List<CommentDto> commentDtos = post.getCommentList().stream()
                .map(comment -> new CommentDto(comment.getCommentId(), comment.getContent()))
                .collect(Collectors.toList());

        return DetailPostResDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrl(image != null ? image.getImageUrl() : null)
                .view(post.getView())
                .likes(post.getLikes())
                .postDate(post.getPostDate())
                .comments(comments != null ? commentDtos : null)
                .build();
    }

    public record CommentDto(Long commentId, String content) {}
}
