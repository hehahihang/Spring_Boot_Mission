package dev.jeongks.curdPractice.controller;

import dev.jeongks.curdPractice.dto.PostDto;
import dev.jeongks.curdPractice.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("board/{boardId}/post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private PostService postService;
    public PostController(
            @Autowired PostService postService
    ){
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(
            @PathVariable("boardId") Long boardId,
            @RequestBody PostDto postDto
    ){
        return ResponseEntity.ok(this.postService.createPost(boardId,postDto));
    }

    @GetMapping
    public ResponseEntity<Collection<PostDto>> readPostAll(
            @PathVariable("boardId") Long boardId
    ){
        Collection<PostDto> postDtoCollection = this.postService.readPostAll(boardId);
        if(postDtoCollection==null || postDtoCollection.size()==0){
            logger.info("post is empty");
            return ResponseEntity.noContent().build();
        }
        logger.info("All post is read now");
        return ResponseEntity.ok(postDtoCollection);
    }

    @GetMapping("{postId}")
    public ResponseEntity<PostDto> readPost(
            @PathVariable("postId") Long postId,
            @PathVariable("boardId") Long boardId
    ){
        PostDto targetPost = this.postService.readPost(boardId,postId);
        if(targetPost==null){
            logger.info("post is not found");
            return ResponseEntity.noContent().build();
        }
        logger.info("post id {} is found",postId);
        return ResponseEntity.ok(targetPost);
    }

    @PutMapping("{postId}")
    public ResponseEntity<?> updatePost(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId,
            @RequestBody PostDto postDto
    ){
        if(this.postService.updatePost(boardId,postId,postDto)){
            logger.info("post id {} is updated",postId);
            PostDto updatedPost = this.postService.readPost(boardId,postId);
            return ResponseEntity.ok(updatedPost);
        }
        else{
            logger.info("post is not found");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<?> deletePost(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId,
            @RequestParam String password
    ){
        if(this.postService.deletePost(boardId,postId,password)){
            logger.info("post id {} is deleted",postId);
            return ResponseEntity.ok(null);
        }
        else{
            logger.info("post is not exist");
            return ResponseEntity.notFound().build();
        }
    }
}
