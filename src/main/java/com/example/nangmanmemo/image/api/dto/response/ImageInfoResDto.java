package com.example.nangmanmemo.image.api.dto.response;

import com.example.nangmanmemo.image.domain.Image;
import lombok.Builder;

@Builder
public record ImageInfoResDto(
        String imageUrl
) {
    public static ImageInfoResDto from(Image image) {
        return ImageInfoResDto.builder()
                .imageUrl(image.getImageUrl())
                .build();
    }
}
