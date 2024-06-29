package com.example.nangmanmemo.image.exception;

import com.example.nangmanmemo.global.error.exception.NotFoundGroupException;

public class ImageEqualsPostIdNotFoundException extends NotFoundGroupException {

public ImageEqualsPostIdNotFoundException(String message) { super(message); }

    public ImageEqualsPostIdNotFoundException(Long postId) {
        this("이미지에 해당하는 게시글이 없습니다. ImageId: " + postId);
    }
}