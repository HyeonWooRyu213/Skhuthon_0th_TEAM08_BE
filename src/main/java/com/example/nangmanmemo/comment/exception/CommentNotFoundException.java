package com.example.nangmanmemo.comment.exception;

import com.example.nangmanmemo.comment.domain.Comment;
import org.webjars.NotFoundException;

public class CommentNotFoundException extends NotFoundException {

    public CommentNotFoundException(String message) {super(message);}

    public CommentNotFoundException(Long commentId) {
        this("게시글에 댓글이 없습니다. commentId: " + commentId);
    }
}

