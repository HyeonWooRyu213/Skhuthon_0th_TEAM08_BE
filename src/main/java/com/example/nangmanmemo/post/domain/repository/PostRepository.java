package com.example.nangmanmemo.post.domain.repository;

import com.example.nangmanmemo.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
