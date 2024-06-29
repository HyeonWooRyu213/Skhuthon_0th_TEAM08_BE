package com.example.nangmanmemo.comment.api.request;

import com.example.nangmanmemo.post.domain.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CommentSaveReqDto(
        @NotBlank
        @Schema(description = "댓글 내용", example = "너무 멋있어요")
        String content,
        @Schema(description = "게시물 ID", example = "1")
        Long postId

) {
}
