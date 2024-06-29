package com.example.nangmanmemo.comment.api.request;

import com.example.nangmanmemo.post.domain.Post;

public record CommentSaveReqDto(
        String content,
        Long postId

) {
}
