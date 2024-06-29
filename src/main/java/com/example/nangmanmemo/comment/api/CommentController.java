package com.example.nangmanmemo.comment.api;

import com.example.nangmanmemo.comment.api.request.CommentSaveReqDto;
import com.example.nangmanmemo.comment.api.request.CommentUpdateReqDto;
import com.example.nangmanmemo.comment.api.response.CommentInfoResDto;
import com.example.nangmanmemo.comment.application.CommentService;
import com.example.nangmanmemo.global.template.RspTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "댓글 등록", description = "댓글을 등록합니다.")
        @ApiResponses(value =  {
                @ApiResponse(responseCode = "200", description = "등록 성공"),
                @ApiResponse(responseCode = "404", description = "댓글 없음")
        })
    @PostMapping
    public RspTemplate<String> commentSave(@RequestBody CommentSaveReqDto commentSaveReqDto) {
        commentService.commentSave(commentSaveReqDto);

        return new RspTemplate<>(HttpStatus.CREATED, "댓글 작성 성공");
    }

    @Operation(summary = "댓글 조회", description = "댓글을 조회합니다.")
    @ApiResponses(value =  {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "댓글 없음")
    })
    @GetMapping("/{commentId}")
    public RspTemplate<CommentInfoResDto> commentFindOne(@PathVariable("commentId") Long commentId) {
        CommentInfoResDto commentInfoResDto = commentService.commentFindOne(commentId);

        return new RspTemplate<>(HttpStatus.OK, "조회 완료!",commentInfoResDto);
    }

    @Operation(summary = "댓글 수정", description = "댓글을 수정합니다.")
    @ApiResponses(value =  {
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "404", description = "댓글 없음")
    })
    @PatchMapping("/{commentId}")
    public RspTemplate<String> commentUpdate(@PathVariable("commentId") Long commentId, @RequestBody CommentUpdateReqDto commentUpdateReqDto) {
        commentService.commentUpdate(commentId, commentUpdateReqDto);

        return new RspTemplate<>(HttpStatus.OK,"댓글 수정");
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다.")
    @ApiResponses(value =  {
            @ApiResponse(responseCode = "200", description = "삭제 성공"),
            @ApiResponse(responseCode = "404", description = "댓글 없음")
    })
    @DeleteMapping("/{commentId}")
    public RspTemplate<String> commentDelete(@PathVariable("commentId") Long commentId) {
        commentService.commentDelete(commentId);

        return new RspTemplate<>(HttpStatus.OK, "댓글 삭제");
    }
}
