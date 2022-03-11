package dev.jeongks.missionTwo.Controller;

import dev.jeongks.missionTwo.Dto.PostDto;
import dev.jeongks.missionTwo.Service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("board/{boardId}/post")
public class PostController {
    private final static Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    public PostController(
            @Autowired PostService postService
    ){
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity createPost(
            @PathVariable("boardId") Long boardId,
            @RequestBody PostDto postDto
    ){
        PostDto postDto1 = this.postService.createPost(boardId,postDto);
        return ResponseEntity.ok(postDto1);
    }

    @GetMapping("{postId}")
    public ResponseEntity readPost(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId
     ){
        PostDto postDto = this.postService.readPost(boardId,postId);
        if(postDto==null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(postDto);
    }

    @GetMapping
    public ResponseEntity readPostAll(
            @PathVariable("boardId") Long boardId
    ){
        Collection<PostDto> postList = this.postService.readPostAll(boardId);
        if(postList==null || postList.size()==0) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(postList);
    }

    @PutMapping("{postId}")
    public ResponseEntity updatePost(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId,
            @RequestBody PostDto postDto
    ){
        if(!postService.updatePost(boardId,postId,postDto)) return ResponseEntity.notFound().build();
        else return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{postId}")
    public ResponseEntity deletePost(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId,
            @RequestParam("password") String password //Password만 Request 파라미터로 받는 이유는?
    ){
        if(!postService.deletePost(boardId,postId,password)) return ResponseEntity.notFound().build();
        else return ResponseEntity.noContent().build();
    }
}
