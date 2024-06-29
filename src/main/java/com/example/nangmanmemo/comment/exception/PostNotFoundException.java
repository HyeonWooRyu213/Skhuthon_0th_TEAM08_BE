package com.example.nangmanmemo.comment.exception;

import com.example.nangmanmemo.global.error.exception.NotFoundGroupException;

public class PostNotFoundException extends NotFoundGroupException {
    public PostNotFoundException(String message) { super(message); }
    public PostNotFoundException(Long postId) {
        this("해당하는 게시글이 없습니다. postId: " + postId);
    }

}
