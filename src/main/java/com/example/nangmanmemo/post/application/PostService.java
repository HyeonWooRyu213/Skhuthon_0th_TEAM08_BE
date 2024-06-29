package com.example.nangmanmemo.post.application;

import com.example.nangmanmemo.post.api.PostController;
import com.example.nangmanmemo.post.api.request.PostSaveReqDto;
import com.example.nangmanmemo.post.api.request.PostUpdateReqDto;
import com.example.nangmanmemo.post.api.response.PostInfoResDto;
import com.example.nangmanmemo.post.domain.Post;
import com.example.nangmanmemo.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    @Transactional
    public void postSave(@RequestBody PostSaveReqDto postSaveReqDto) {
        Post post = Post.builder()
                .title(postSaveReqDto.title())
                .content(postSaveReqDto.content())
                .build();

        postRepository.save(post);
    }

    public List<PostInfoResDto> postFindAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostInfoResDto::from)
                .collect(Collectors.toList());
    }

    public PostInfoResDto postFindOne(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글 없음"));

        return PostInfoResDto.from(post);
    }

    @Transactional
    public void postUpdate(Long postId, PostUpdateReqDto postUpdateReqDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글 없음"));

        post.update(postUpdateReqDto);
    }

    @Transactional
    public void postDelete(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글 없음"));

        postRepository.delete(post);
    }




}
