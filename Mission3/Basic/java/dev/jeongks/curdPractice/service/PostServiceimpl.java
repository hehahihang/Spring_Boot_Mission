package dev.jeongks.curdPractice.service;

import dev.jeongks.curdPractice.dto.PostDto;
import dev.jeongks.curdPractice.entity.BoardEntity;
import dev.jeongks.curdPractice.entity.PostEntity;
import dev.jeongks.curdPractice.entity.UserEntity;
import dev.jeongks.curdPractice.repository.BoardRespository;
import dev.jeongks.curdPractice.repository.PostRepository;
import dev.jeongks.curdPractice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceimpl implements PostService{
    private static final Logger logger = LoggerFactory.getLogger(PostServiceimpl.class);
    private PostRepository postRepository;
    private BoardRespository boardRespository;
    private UserRepository userRepository;

    public PostServiceimpl(
            @Autowired PostRepository postRepository,
            @Autowired BoardRespository boardRespository,
            @Autowired UserRepository userRepository
    ){
        this.postRepository = postRepository;
        this.boardRespository = boardRespository;
        this.userRepository = userRepository;
    }

    @Override
    public PostDto createPost(Long boardId, PostDto postDto) {
        //연결된 Board가 없으면 throw 처리
        if(!this.boardRespository.existsById(boardId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        //User가 존재하지 않으면 throw
        if(!this.userRepository.existsById(postDto.getUserId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Optional<BoardEntity> optionalBoardEntity = this.boardRespository.findById(boardId);
        BoardEntity boardEntity = optionalBoardEntity.get();
        Optional<UserEntity> optionalUserEntity = this.userRepository.findById(postDto.getUserId());
        UserEntity userEntity = optionalUserEntity.get();

        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postDto.getTitle());
        postEntity.setContent(postDto.getContent());
        postEntity.setUserEntity(userEntity);
        postEntity.setBoardEntity(boardEntity);
        postEntity = this.postRepository.save(postEntity);

        PostDto newPost = new PostDto();
        newPost.setId(postEntity.getId());
        newPost.setTitle(postEntity.getTitle());
        newPost.setContent(postEntity.getContent());
        newPost.setBoardId(postEntity.getBoardEntity().getId());
        newPost.setBoardId(postEntity.getUserEntity().getId());
        return newPost;
    }

    @Override
    public PostDto readPost(Long boardId, Long postId) {
        if(!this.boardRespository.existsById(boardId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        PostEntity postEntity = this.postRepository.findById(postId).get();
        if(!postEntity.getBoardEntity().getId().equals(boardId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return new PostDto(
                postEntity.getId(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getBoardEntity().getId(),
                postEntity.getUserEntity().getId()
        );
    }

    @Override
    public Collection<PostDto> readPostAll(Long boardId) {
        Optional<BoardEntity> optionalBoardEntity = this.boardRespository.findById(boardId);
        if(optionalBoardEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        BoardEntity boardEntity = optionalBoardEntity.get();
        Iterable<PostEntity> iterable = boardEntity.getPostEntityList();
        List<PostDto> postDtoList = new ArrayList<>();

        for(PostEntity postEntity : iterable){
            postDtoList.add(new PostDto(
                    postEntity.getId(),
                    postEntity.getTitle(),
                    postEntity.getContent(),
                    postEntity.getBoardEntity().getId(),
                    postEntity.getUserEntity().getId()
            ));
        }

        return postDtoList;
    }

    @Override
    public boolean updatePost(Long boardId, Long postId, PostDto postDto) {

        //Post가 없다면
        if(!this.postRepository.existsById(postId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        PostEntity postEntity = this.postRepository.findById(postId).get();

        //요청과 찾은 Post의 id가 다름
        if(!postEntity.getBoardEntity().getId().equals(boardId)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        //사용자가 달라서 FORBIDDNE 거절
        if(!postEntity.getUserEntity().getId().equals(postDto.getUserId())) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        postEntity.setTitle(postDto.getTitle()!=null ? postDto.getTitle() : postEntity.getTitle());
        postEntity.setContent(postDto.getContent()!=null ? postDto.getContent() : postEntity.getContent());
        this.postRepository.save(postEntity);

        return true;
    }

    @Override
    public boolean deletePost(Long boardId, Long postId, String password) {
        //Post가 존재하지 않으면 throw
        if(!this.postRepository.existsById(postId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        PostEntity postEntity = this.postRepository.findById(postId).get();

        //
        if(!postEntity.getBoardEntity().getId().equals(boardId)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        this.postRepository.delete(postEntity);

        return true;
    }
}
