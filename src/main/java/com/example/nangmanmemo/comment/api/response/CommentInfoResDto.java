package com.example.nangmanmemo.comment.api.response;

import com.example.nangmanmemo.comment.domain.Comment;
import com.example.nangmanmemo.post.domain.Post;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CommentInfoResDto(
    @NotBlank
    String content,
    Long postId
) {
    public static CommentInfoResDto from (Comment comment) {
        return CommentInfoResDto.builder()
                .content(comment.getContent())

                .build();
    }
}
