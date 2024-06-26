package com.example.nangmanmemo.global.error.dto;

public record ErrorResponse(
        int statusCode,
        String message
) {
}
