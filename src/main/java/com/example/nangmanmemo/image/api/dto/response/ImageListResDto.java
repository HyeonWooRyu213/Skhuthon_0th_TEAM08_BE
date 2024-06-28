package com.example.nangmanmemo.image.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ImageListResDto(
        List<ImageInfoResDto> images
) {
    public static ImageListResDto from(List<ImageInfoResDto> images) {
        return ImageListResDto.builder()
                .images(images)
                .build();
    }
}
