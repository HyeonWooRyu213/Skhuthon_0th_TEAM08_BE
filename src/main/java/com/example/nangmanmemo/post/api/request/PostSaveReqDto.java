package com.example.nangmanmemo.post.api.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record PostSaveReqDto(
        @NotBlank
        @Max(value = 20)
        String title,
        @NotBlank
        String content,
        @NotBlank
        int view,
        @NotBlank
        int likes
) {
}
