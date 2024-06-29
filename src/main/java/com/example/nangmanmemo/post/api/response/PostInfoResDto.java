package com.example.nangmanmemo.post.api.response;

import com.example.nangmanmemo.comment.domain.Comment;
import com.example.nangmanmemo.image.domain.Image;
import com.example.nangmanmemo.post.domain.Post;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;

@Builder
public record PostInfoResDto(
        Long postId,
        @NotBlank
        @Max(value = 20)
        String title,
        @NotBlank
        String content
) {
    public static PostInfoResDto from (Post post) {
        return PostInfoResDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }
}
