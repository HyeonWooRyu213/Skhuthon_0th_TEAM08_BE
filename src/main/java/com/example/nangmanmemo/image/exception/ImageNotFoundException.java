package com.example.nangmanmemo.image.exception;

import com.example.nangmanmemo.global.error.exception.NotFoundGroupException;

public class ImageNotFoundException extends NotFoundGroupException {
    public ImageNotFoundException(String message) { super(message); }

    public ImageNotFoundException(Long imageId) {
        this("해당하는 이미지가 없습니다. ImageId: " + imageId);
    }
}
