package com.example.nangmanmemo.image.application;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.nangmanmemo.image.api.dto.request.ImageUpdateReqDto;
import com.example.nangmanmemo.image.domain.ImageEntity;
import com.example.nangmanmemo.image.domain.Post;
import com.example.nangmanmemo.image.domain.repository.ImageRepository;
import com.example.nangmanmemo.image.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
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
    private final PostRepository postRepository;

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

    public void saveImageInfo(Long postId, String imageUrl) {
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid postId: " + postId));

        ImageEntity imageEntity = ImageEntity.builder()
                .imageUrl(imageUrl)
                .post(post)
                .build();
        imageRepository.save(imageEntity);
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
            imageRepository.delete(image);
            amazonS3.deleteObject(new DeleteObjectRequest(bucketName, fileName));
        } else {
            throw new IllegalArgumentException("Image not found with id: " + imageId);
        }
    }

    private String generateFileName(MultipartFile file) {
        return UUID.randomUUID().toString() + "-" + Objects.requireNonNull(file.getOriginalFilename()).replace(" ", "_");
    }

}

