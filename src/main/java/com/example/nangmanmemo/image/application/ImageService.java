package com.example.nangmanmemo.image.application;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.nangmanmemo.image.domain.Image;
import com.example.nangmanmemo.image.domain.repository.ImageRepository;
import com.example.nangmanmemo.image.exception.ImageNotFoundException;
import com.example.nangmanmemo.image.exception.PostNotFoundException;
import com.example.nangmanmemo.post.api.response.PostImageInfoResDto;
import com.example.nangmanmemo.post.api.response.PostInfoResDto;
import com.example.nangmanmemo.post.domain.Post;
import com.example.nangmanmemo.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final AmazonS3 amazonS3;
    private final ImageRepository imageRepository;
    private final PostRepository postRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;


    @Transactional
    public String upload(MultipartFile file) {
        try {
            String fileName = generateFileName(file);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            return amazonS3.getUrl(bucketName, fileName).toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while uploading the file to S3", e);
        }
    }

    @Transactional
    public PostImageInfoResDto saveImageInfo(Long postId, String imageUrl) {
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));

        Image image = Image.builder()
                .imageUrl(imageUrl)
                .post(post)
                .build();
        imageRepository.save(image);

        return PostImageInfoResDto.from(image, post);

    }

    @Transactional
    public void deleteImage(Long imageId) {
        Optional<Image> optionalImage = Optional.ofNullable(imageRepository.findById(imageId).orElseThrow(() -> new ImageNotFoundException(imageId)));
            Image image = optionalImage.get();
            String fileName = image.getImageUrl().substring(image.getImageUrl().lastIndexOf("/") + 1);
            imageRepository.delete(image);
            amazonS3.deleteObject(new DeleteObjectRequest(bucketName, fileName));

    }

    private String generateFileName(MultipartFile file) {
        return UUID.randomUUID().toString() + "-" + Objects.requireNonNull(file.getOriginalFilename()).replace(" ", "_");
    }

}

