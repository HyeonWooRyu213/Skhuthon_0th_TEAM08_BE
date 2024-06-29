package com.example.nangmanmemo.comment.domain.repository;

import com.example.nangmanmemo.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
