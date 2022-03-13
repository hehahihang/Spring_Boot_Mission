package dev.jeongks.curdPractice.service;

import dev.jeongks.curdPractice.dto.PostDto;
import dev.jeongks.curdPractice.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PostServiceimpl implements PostService{
    private static final Logger logger = LoggerFactory.getLogger(PostServiceimpl.class);
    private PostRepository postRepository;

    public PostServiceimpl(
            @Autowired PostRepository postRepository
    ){
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(Long boardId, PostDto postDto) {
        return this.postRepository.create(boardId,postDto);
    }

    @Override
    public PostDto readPost(Long boardId, Long postId) {
        return this.postRepository.read(boardId,postId);
    }

    @Override
    public Collection<PostDto> readPostAll(Long boardId) {
        return this.postRepository.readAll(boardId);
    }

    @Override
    public boolean updatePost(Long boardId, Long postId, PostDto postDto) {
        return this.postRepository.update(boardId,postId,postDto);
    }

    @Override
    public boolean deletePost(Long boardId, Long postId, String password) {
        return this.postRepository.delete(boardId,postId,password);
    }
}
