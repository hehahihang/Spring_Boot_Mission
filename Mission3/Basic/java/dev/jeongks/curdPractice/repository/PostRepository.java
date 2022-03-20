package dev.jeongks.curdPractice.repository;

import dev.jeongks.curdPractice.dto.PostDto;
import dev.jeongks.curdPractice.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PostRepository extends CrudRepository<PostEntity,Long> {
//    PostDto create(Long boardId,PostDto postDto);
//    PostDto read(Long boardId,Long postId);
//    Collection<PostDto> readAll(Long boardId);
//    boolean update(Long boardId, Long postId, PostDto postDto);
//    boolean delete(Long boardId, Long postId, String password);
}
