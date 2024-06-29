package com.example.nangmanmemo.post.api.response;

import com.example.nangmanmemo.post.domain.Post;
import lombok.Builder;

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
