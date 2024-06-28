package com.example.nangmanmemo.image.api.dto.response;

import com.example.nangmanmemo.image.domain.ImageEntity;
import lombok.Builder;

@Builder
public record ImageInfoResDto(
        String imageUrl
) {
    public static ImageInfoResDto from(ImageEntity imageEntity) {
        return ImageInfoResDto.builder()
                .imageUrl(imageEntity.getImageUrl())
                .build();
    }
}
