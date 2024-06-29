package com.example.nangmanmemo.comment.api.response;

import com.example.nangmanmemo.comment.domain.Comment;
import lombok.Builder;

@Builder
public record CommentInfoResDto(
    String content
) {
    public static CommentInfoResDto from (Comment comment) {
        return CommentInfoResDto.builder()
                .content(comment.getContent())
                .build();
    }
}
