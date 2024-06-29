package com.example.nangmanmemo.post.api;

import com.example.nangmanmemo.image.api.dto.response.ImageInfoResDto;
import com.example.nangmanmemo.image.application.ImageService;
import com.example.nangmanmemo.image.domain.repository.ImageRepository;
import com.example.nangmanmemo.post.api.request.PostSaveReqDto;
import com.example.nangmanmemo.post.api.request.PostUpdateReqDto;
import com.example.nangmanmemo.post.api.response.DetailPostResDto;
import com.example.nangmanmemo.post.api.response.PostInfoResDto;
import com.example.nangmanmemo.post.api.response.PostListResDto;
import com.example.nangmanmemo.post.application.PostService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<String> likePost(@PathVariable Long postId) {
        postService.incrementLike(postId);
        return ResponseEntity.ok().build();
    }


    @PostMapping
    public ResponseEntity<String> postSave(@RequestBody PostSaveReqDto postSaveReqDto) {
        postService.postSave(postSaveReqDto);

        return new ResponseEntity<>("게시글 업로드", HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<PostListResDto> postFindAll() {
        List<PostInfoResDto> posts = postService.postFindAll();
        PostListResDto postListResDto = PostListResDto.from(posts);

        return new ResponseEntity<>(postListResDto, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<DetailPostResDto> postFindOne(@PathVariable("postId") Long postId) {
        DetailPostResDto postInfoResDto = postService.postFindOne(postId);

        return new ResponseEntity<>(postInfoResDto, HttpStatus.OK);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<String> postUpdate(@PathVariable("postId") Long postId, @RequestBody PostUpdateReqDto postUpdateReqDto) {
        postService.postUpdate(postId, postUpdateReqDto);

        return new ResponseEntity<>("게시글 수정", HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> postDelete(@PathVariable("postId") Long postId) {
        postService.postDelete(postId);

        return new ResponseEntity<>("게시글 삭제", HttpStatus.OK);
    }
}
