package com.example.nangmanmemo.image.api;

import com.example.nangmanmemo.image.api.dto.request.ImageSaveReqDto;
import com.example.nangmanmemo.image.api.dto.request.ImageUpdateReqDto;
import com.example.nangmanmemo.image.api.dto.response.ImageInfoResDto;
import com.example.nangmanmemo.image.application.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final AwsS3Service awsS3Service;

    @PostMapping("/upload")
    public ResponseEntity<ImageInfoResDto> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("postId") Long postId) {
        try {
            String imageUrl = awsS3Service.upload(file);
            ImageSaveReqDto saveReqDto = new ImageSaveReqDto(postId, imageUrl);
            awsS3Service.saveImageInfo(saveReqDto, imageUrl);
            return ResponseEntity.ok(new ImageInfoResDto(imageUrl));
        } catch (IOException e) {
            return ResponseEntity.status(500).build();  // 서버 에러 응답
        }
    }

    @PostMapping("/uploadMultiple")
    public ResponseEntity<List<ImageInfoResDto>> uploadMultipleImages(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("postId") Long postId) {
        try {
            List<String> imageUrls = awsS3Service.uploadMultipleImages(files);
            List<ImageInfoResDto> response = imageUrls.stream()
                    .map(url -> {
                        ImageSaveReqDto saveReqDto = new ImageSaveReqDto(postId, url);
                        awsS3Service.saveImageInfo(saveReqDto, url);
                        return new ImageInfoResDto(url);
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();  // 서버 에러 응답
        }
    }

    @PutMapping("/update/{imageId}")
    public ResponseEntity<Void> updateImage(
            @PathVariable Long imageId,
            @RequestBody ImageUpdateReqDto request) {
        awsS3Service.updateImageInfo(imageId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long imageId) {
        awsS3Service.deleteImage(imageId);
        return ResponseEntity.noContent().build();
    }


}
