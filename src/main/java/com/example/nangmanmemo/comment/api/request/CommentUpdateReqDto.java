package com.example.nangmanmemo.comment.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CommentUpdateReqDto(
        @NotBlank
        @Schema(description = "댓글 내용", example = "너무 멋있어요")
        String content
) {
}
