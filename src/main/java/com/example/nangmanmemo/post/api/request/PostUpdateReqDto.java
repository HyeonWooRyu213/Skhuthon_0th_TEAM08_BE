package com.example.nangmanmemo.post.api.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record PostUpdateReqDto(
        @NotBlank
        @Max(value = 20)
        String title,
        @NotBlank
        String content,
        @NotBlank
        Integer view,
        @NotBlank
        Integer likes
) {
}
