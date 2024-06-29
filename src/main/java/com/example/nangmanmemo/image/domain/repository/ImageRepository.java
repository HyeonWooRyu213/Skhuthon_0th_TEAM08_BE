package com.example.nangmanmemo.image.domain.repository;

import com.example.nangmanmemo.image.domain.ImageEntity;
import com.example.nangmanmemo.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    List<ImageEntity> findByPost(Post post);
}
