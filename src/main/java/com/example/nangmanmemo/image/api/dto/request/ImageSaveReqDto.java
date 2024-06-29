package com.example.nangmanmemo.image.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ImageSaveReqDto(
        // 게시글 아이디, 이미지 url
        @NotNull
        Long postId,
        @NotBlank
        String imageUrl
) {
}
