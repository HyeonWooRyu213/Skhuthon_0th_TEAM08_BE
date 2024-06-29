package com.example.nangmanmemo.post.api.response;

import com.example.nangmanmemo.comment.domain.Comment;
import com.example.nangmanmemo.image.domain.Image;
import com.example.nangmanmemo.post.domain.Post;
import lombok.Builder;

import java.util.List;

@Builder
public record PostInfoResDto(
        String title,
        String content
) {
    public static PostInfoResDto from (Post post) {
        return PostInfoResDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }
}
