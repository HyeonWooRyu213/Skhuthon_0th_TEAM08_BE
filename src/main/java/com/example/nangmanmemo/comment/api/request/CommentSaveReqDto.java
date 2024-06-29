package com.example.nangmanmemo.comment.api.request;

import com.example.nangmanmemo.post.domain.Post;
import jakarta.validation.constraints.NotBlank;

public record CommentSaveReqDto(
        @NotBlank
        String content,
        Long postId

) {
}
