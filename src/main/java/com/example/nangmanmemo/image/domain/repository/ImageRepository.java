package com.example.nangmanmemo.image.domain.repository;

import com.example.nangmanmemo.image.domain.Image;

import com.example.nangmanmemo.post.domain.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByPost(Post post);

    Image findByPostPostId(Long postId);
}
