package com.example.nangmanmemo.post.application;

import com.example.nangmanmemo.comment.domain.Comment;
import com.example.nangmanmemo.comment.domain.repository.CommentRepository;
import com.example.nangmanmemo.image.domain.Image;
import com.example.nangmanmemo.image.domain.repository.ImageRepository;
import com.example.nangmanmemo.post.api.PostController;
import com.example.nangmanmemo.post.api.request.PostSaveReqDto;
import com.example.nangmanmemo.post.api.request.PostUpdateReqDto;
import com.example.nangmanmemo.post.api.response.DetailPostResDto;
import com.example.nangmanmemo.post.api.response.PostInfoResDto;
import com.example.nangmanmemo.post.domain.Post;
import com.example.nangmanmemo.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final ImageRepository imageRepository;

    // 게시글 저장
    @Transactional
    public void postSave(@RequestBody PostSaveReqDto postSaveReqDto) {
        Post post = Post.builder()
                .title(postSaveReqDto.title())
                .content(postSaveReqDto.content())
                .build();

        postRepository.save(post);
    }

    // 게시글 전체 조회
    public List<PostInfoResDto> postFindAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostInfoResDto::from)
                .collect(Collectors.toList());
    }

    // 게시글 상세조회
    @Transactional
    public DetailPostResDto postFindOne(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글 없음"));

        // 조회수 증가
        post.incrementView();
        postRepository.save(post);

        List<Comment> comments = commentRepository.findByPostPostId(postId);
        Image image = imageRepository.findByPostPostId(postId);

        return DetailPostResDto.from(post, comments, image);
    }

    // 좋아요 증가
    @Transactional
    public void incrementLike(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음"));

        post.incrementLike();
        postRepository.save(post);
    }

    // 게시글 업데이트
    @Transactional
    public void postUpdate(Long postId, PostUpdateReqDto postUpdateReqDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글 없음"));

        post.update(postUpdateReqDto);
    }

    // 게시글 삭제
    @Transactional
    public void postDelete(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글 없음"));

        postRepository.delete(post);
    }




}
