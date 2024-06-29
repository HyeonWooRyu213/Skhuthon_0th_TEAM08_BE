package com.example.nangmanmemo.comment.domain.repository;

import com.example.nangmanmemo.comment.domain.Comment;
import com.example.nangmanmemo.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
       List<Comment> findByPostPostId(Long postId);


}
