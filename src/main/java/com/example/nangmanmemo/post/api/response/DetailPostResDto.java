package com.example.nangmanmemo.post.api.response;

import com.example.nangmanmemo.comment.domain.Comment;
import com.example.nangmanmemo.image.domain.Image;
import com.example.nangmanmemo.post.domain.Post;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record DetailPostResDto(
        Long postId,
        @NotBlank
        @Max(value = 20)
        String title,
        @NotBlank
        String content,
        @NotBlank
        String imageUrl,
        @NotBlank
        int likes,
        @NotBlank
        int view,
        LocalDateTime postDate,
        @NotNull
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
