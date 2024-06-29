package com.example.nangmanmemo.image.application;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.nangmanmemo.image.api.dto.request.ImageSaveReqDto;
import com.example.nangmanmemo.image.api.dto.request.ImageUpdateReqDto;
import com.example.nangmanmemo.image.domain.ImageEntity;
import com.example.nangmanmemo.image.domain.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AwsS3Service {

    private final AmazonS3 amazonS3;
    private final ImageRepository imageRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public String upload(MultipartFile file) throws IOException{
        String fileName = generateFileName(file);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3.getUrl(bucketName, fileName).toString();
    }

    public void saveImageInfo(ImageSaveReqDto request, String imageUrl) {
        ImageEntity imageEntity = ImageEntity.builder()
                .imageUrl(imageUrl)
                .postId(request.postId())
                .build();
        imageRepository.save(imageEntity);
    }


    public List<String> uploadMultipleImages(List<MultipartFile> files) throws IOException {
        return files.stream()
                .map(file -> {
                    try {
                        return upload(file);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to upload file: " + file.getOriginalFilename(), e);
                    }
                })
                .collect(Collectors.toList());
    }

    public void updateImageInfo(Long imageId, ImageUpdateReqDto request) {
        Optional<ImageEntity> optionalImage = imageRepository.findById(imageId);
        if (optionalImage.isPresent()) {
            ImageEntity image = optionalImage.get();
            image.updateImage(request.imageUrl());
            imageRepository.save(image);
        } else {
            throw new IllegalArgumentException("Image not found with id: " + imageId);
        }
    }

    public void deleteImage(Long imageId) {
        Optional<ImageEntity> optionalImage = imageRepository.findById(imageId);
        if (optionalImage.isPresent()) {
            ImageEntity image = optionalImage.get();
            String fileName = image.getImageUrl().substring(image.getImageUrl().lastIndexOf("/") + 1);
            amazonS3.deleteObject(new DeleteObjectRequest(bucketName, fileName));
            imageRepository.delete(image);
        } else {
            throw new IllegalArgumentException("Image not found with id: " + imageId);
        }
    }

    private String generateFileName(MultipartFile file) {
        return UUID.randomUUID().toString() + "-" + Objects.requireNonNull(file.getOriginalFilename()).replace(" ", "_");
    }

}

