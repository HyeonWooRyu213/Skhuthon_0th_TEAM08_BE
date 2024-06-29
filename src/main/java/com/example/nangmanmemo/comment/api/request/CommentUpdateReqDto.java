package com.example.nangmanmemo.comment.api.request;

import jakarta.validation.constraints.NotBlank;

public record CommentUpdateReqDto(
        @NotBlank
        String content
) {
}
