package com.example.nangmanmemo.comment.application;

import com.example.nangmanmemo.comment.api.request.CommentSaveReqDto;
import com.example.nangmanmemo.comment.api.request.CommentUpdateReqDto;
import com.example.nangmanmemo.comment.api.response.CommentInfoResDto;
import com.example.nangmanmemo.comment.domain.Comment;
import com.example.nangmanmemo.comment.domain.repository.CommentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public void commentSave(@RequestBody CommentSaveReqDto commentSaveReqDto) {
        Comment comment = Comment.builder()
                .content(commentSaveReqDto.content())
                .build();
        commentRepository.save(comment);
    }

    public CommentInfoResDto commentFindOne(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글 없음"));

        return CommentInfoResDto.from(comment);
    }

    public void commentUpdate(Long commentId, CommentUpdateReqDto commentUpdateReqDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글 없음"));

        comment.update(commentUpdateReqDto);
    }

    public void commentDelete(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글 없음"));

        commentRepository.delete(comment);
    }
}
