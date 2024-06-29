package com.example.nangmanmemo.post.api.response;

import lombok.Builder;

import java.util.List;

@Builder
public record PostListResDto(
        List<PostInfoResDto> posts
) {
    public static PostListResDto from (List<PostInfoResDto> posts) {
        return PostListResDto.builder()
                .posts(posts)
                .build();
    }
}
