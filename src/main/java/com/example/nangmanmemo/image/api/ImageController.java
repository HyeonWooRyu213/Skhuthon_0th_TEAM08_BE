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

    @PostMapping(value = "/upload",consumes = "multipart/form-data")
    public ResponseEntity<ImageInfoResDto> upload(
            @RequestPart("file") MultipartFile file,
            @RequestParam("postId") Long postId) {
        try {
            String imageUrl = awsS3Service.upload(file);

            awsS3Service.saveImageInfo(postId, imageUrl);
            return ResponseEntity.ok(new ImageInfoResDto(imageUrl));
        } catch (IOException e) {
            return ResponseEntity.status(500).build();  // 서버 에러 응답
        }
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long imageId) {
        awsS3Service.deleteImage(imageId);
        return ResponseEntity.noContent().build();
    }


}
