package com.example.nangmanmemo.image.api;

import com.example.nangmanmemo.global.template.RspTemplate;
import com.example.nangmanmemo.image.api.dto.response.ImageInfoResDto;
import com.example.nangmanmemo.image.application.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @DeleteMapping("/{imageId}")
    public RspTemplate<Void> deleteImage(@PathVariable Long imageId) {
        imageService.deleteImage(imageId);
        return new RspTemplate<>(HttpStatus.OK, "삭제 완료!");
    }


}
