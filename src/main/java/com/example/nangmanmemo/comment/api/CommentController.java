package com.example.nangmanmemo.comment.api;

import com.example.nangmanmemo.comment.api.request.CommentSaveReqDto;
import com.example.nangmanmemo.comment.api.request.CommentUpdateReqDto;
import com.example.nangmanmemo.comment.api.response.CommentInfoResDto;
import com.example.nangmanmemo.comment.application.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<String> commentSave(@RequestBody CommentSaveReqDto commentSaveReqDto) {
        commentService.commentSave(commentSaveReqDto);

        return new ResponseEntity<>("댓글 작성 성공", HttpStatus.CREATED);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentInfoResDto> commentFindOne(@PathVariable("commentId") Long commentId) {
        CommentInfoResDto commentInfoResDto = commentService.commentFindOne(commentId);

        return new ResponseEntity<>(commentInfoResDto, HttpStatus.OK);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<String> commentUpdate(@PathVariable("commentId") Long commentId, @RequestBody CommentUpdateReqDto commentUpdateReqDto) {
        commentService.commentUpdate(commentId, commentUpdateReqDto);

        return new ResponseEntity<>("댓글 수정",HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> commentDelete(@PathVariable("commentId") Long commentId) {
        commentService.commentDelete(commentId);

        return new ResponseEntity<>("댓글 삭제", HttpStatus.OK);
    }
}
