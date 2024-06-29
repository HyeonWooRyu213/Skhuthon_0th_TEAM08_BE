package com.example.nangmanmemo.post.api.response;

import com.example.nangmanmemo.image.api.dto.response.ImageInfoResDto;
import com.example.nangmanmemo.image.domain.Image;
import com.example.nangmanmemo.post.domain.Post;
import lombok.Builder;

@Builder
public record PostImageInfoResDto(
        Long postId,
        String title,
        String content,
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
