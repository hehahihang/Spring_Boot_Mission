package dev.jeongks.curdPractice.service;

import dev.jeongks.curdPractice.dto.PostDto;

import java.util.Collection;

public interface PostService {
    PostDto createPost(Long boardId, PostDto postDto);
    PostDto readPost(Long boardId, Long postId);
    Collection<PostDto> readPostAll(Long boardId);
    boolean updatePost(Long boardId, Long postId, PostDto postDto);
    boolean deletePost(Long boardId, Long postId, String password);
}
