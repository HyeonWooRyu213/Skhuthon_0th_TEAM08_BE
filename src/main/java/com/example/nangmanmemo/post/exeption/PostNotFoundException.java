package com.example.nangmanmemo.post.exeption;

import org.webjars.NotFoundException;

public class PostNotFoundException extends NotFoundException {

    public PostNotFoundException(String message) { super(message); }

    public PostNotFoundException(Long postId) {this ("게시글이 존재하지 않습니다. id : "+ postId);}
}
