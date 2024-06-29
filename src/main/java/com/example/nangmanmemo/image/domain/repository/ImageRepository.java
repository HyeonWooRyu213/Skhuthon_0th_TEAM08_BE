package com.example.nangmanmemo.image.domain.repository;

import com.example.nangmanmemo.image.domain.ImageEntity;

import com.example.nangmanmemo.post.domain.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    List<ImageEntity> findByPost(Post post);
}
