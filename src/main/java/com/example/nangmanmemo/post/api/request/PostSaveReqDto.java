package com.example.nangmanmemo.post.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record PostSaveReqDto(
        @NotBlank
        @Schema(description = "게시글 제목", example = "이건 제목이야")
        @Max(value = 20)
        String title,
        @NotBlank
        @Schema(description = "게시글 내용", example = "이건 내용이야")
        String content
) {
}
