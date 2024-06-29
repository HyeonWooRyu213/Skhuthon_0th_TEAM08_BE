package com.example.nangmanmemo.post.api.response;

import com.example.nangmanmemo.image.api.dto.response.ImageInfoResDto;
import com.example.nangmanmemo.image.domain.Image;
import com.example.nangmanmemo.post.domain.Post;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record PostImageInfoResDto(
        Long postId,
        @NotBlank
        @Max(value = 20)
        String title,
        @NotBlank
        String content,
        @NotBlank
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
