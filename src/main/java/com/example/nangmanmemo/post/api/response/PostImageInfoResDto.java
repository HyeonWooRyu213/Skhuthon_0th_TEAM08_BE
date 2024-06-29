package com.example.nangmanmemo.post.api.response;

import com.example.nangmanmemo.image.api.dto.response.ImageInfoResDto;
import com.example.nangmanmemo.image.domain.Image;
import com.example.nangmanmemo.post.domain.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record PostImageInfoResDto(
        @Schema(description = "게시글 id", example = "1")
        Long postId,
        @NotBlank
        @Max(value = 20)
        @Schema(description = "게시글 제목", example = "이건 제목이야")
        String title,
        @NotBlank
        @Schema(description = "게시글 내용", example = "이건 내용이야")
        String content,
        @NotBlank
        @Schema(description = "이미지 주소", example = "https://sadfh.png")
        String imageUrl
) {
    public static PostImageInfoResDto from(Image image, Post post) {
        return PostImageInfoResDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrl(image.getImageUrl())
                .build();
    }
}
