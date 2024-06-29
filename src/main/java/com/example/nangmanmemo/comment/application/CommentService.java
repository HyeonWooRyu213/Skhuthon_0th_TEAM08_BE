package com.example.nangmanmemo.comment.application;

import com.example.nangmanmemo.comment.api.request.CommentSaveReqDto;
import com.example.nangmanmemo.comment.api.request.CommentUpdateReqDto;
import com.example.nangmanmemo.comment.api.response.CommentInfoResDto;
import com.example.nangmanmemo.comment.domain.Comment;
import com.example.nangmanmemo.comment.domain.repository.CommentRepository;
import com.example.nangmanmemo.comment.exception.CommentNotFoundException;
import com.example.nangmanmemo.post.domain.Post;
import com.example.nangmanmemo.post.domain.repository.PostRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public void commentSave(@RequestBody CommentSaveReqDto commentSaveReqDto) {
        Post post = postRepository.findById(commentSaveReqDto.postId())
                .orElseThrow(()-> new CommentNotFoundException(commentSaveReqDto.postId()));

        Comment comment = Comment.builder()
                .content(commentSaveReqDto.content())
                .post(post)
                .build();
        commentRepository.save(comment);
    }

    public CommentInfoResDto commentFindOne(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new CommentNotFoundException(commentId));

        return CommentInfoResDto.from(comment);
    }

    public void commentUpdate(Long commentId, CommentUpdateReqDto commentUpdateReqDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new CommentNotFoundException(commentId));

        comment.update(commentUpdateReqDto);
    }

    public void commentDelete(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new CommentNotFoundException(commentId));

        commentRepository.delete(comment);
    }
}
