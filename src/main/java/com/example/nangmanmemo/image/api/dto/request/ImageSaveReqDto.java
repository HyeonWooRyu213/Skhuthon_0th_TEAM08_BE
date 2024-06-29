package com.example.nangmanmemo.image.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ImageSaveReqDto(
        // 게시글 아이디, 이미지 url
        @NotNull

        Long postId,
        @NotBlank
        @Schema(description = "이미지 URL", example = "https://footmarkimage.s3.ap-northeast-2.amazonaws.com/~.png")
        String imageUrl
) {
}
