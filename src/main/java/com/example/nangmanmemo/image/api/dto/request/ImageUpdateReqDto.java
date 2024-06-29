package com.example.nangmanmemo.image.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ImageUpdateReqDto(
        @NotBlank
        String imageUrl) {

}
