package dev.jeongks.missionTwo.Service;

import dev.jeongks.missionTwo.Dto.PostDto;
import dev.jeongks.missionTwo.Repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PostServiceImpl implements PostService{
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
    private PostRepository postRepository;

    public PostServiceImpl(
            @Autowired PostRepository postRepository
    ){
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(Long boardId, PostDto postDto) {
        logger.info("create Post");
        return this.postRepository.create(boardId,postDto);
    }

    @Override
    public PostDto readPost(Long boardId, Long postId) {
        logger.info("read Post One");
        return this.postRepository.read(boardId,postId);
    }

    @Override
    public Collection<PostDto> readPostAll(Long boardId) {
        logger.info("read Post All");
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
