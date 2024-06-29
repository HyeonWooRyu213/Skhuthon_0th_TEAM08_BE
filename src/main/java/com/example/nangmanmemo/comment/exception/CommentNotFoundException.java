package com.example.nangmanmemo.comment.exception;

import com.example.nangmanmemo.global.error.exception.NotFoundGroupException;

public class CommentNotFoundException extends NotFoundGroupException {
    public CommentNotFoundException(String message) { super(message); }
    public CommentNotFoundException(Long commentId) {
        this("해당하는 댓글이 없습니다. commentId: " + commentId);
    }
}
