package com.example.nangmanmemo.post.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record PostUpdateReqDto(
        @NotBlank
        @Max(value = 20)
        @Schema(description = "게시글 제목", example = "이건 제목이야")
        String title,
        @NotBlank
        @Schema(description = "게시글 내용", example = "이건 내용이야")
        String content,
        @NotBlank
        @Schema(description = "게시글 조회수", example = "3000")
        Integer view,
        @NotBlank
        @Schema(description = "게시글 좋아요 수", example = "104")
        Integer likes
) {
}
