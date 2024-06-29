package com.example.nangmanmemo.post.api.request;

public record PostUpdateReqDto(
        String title,
        String content,
        Integer view,
        Integer likes
) {
}
