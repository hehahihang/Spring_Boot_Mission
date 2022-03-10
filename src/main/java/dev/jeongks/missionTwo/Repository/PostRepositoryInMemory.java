package dev.jeongks.missionTwo.Repository;

import dev.jeongks.missionTwo.Dto.BoardDto;
import dev.jeongks.missionTwo.Dto.PostDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PostRepositoryInMemory implements PostRepository{
    private static final Logger logger = LoggerFactory.getLogger(PostRepositoryInMemory.class);
    private final BoardRepository boardRepository;
    private Long index = 0L;
    private Map<Long,PostDto> postDtoMap = new HashMap<>();

    public PostRepositoryInMemory(
            @Autowired BoardRepository boardRepository
    ) {
        this.boardRepository = boardRepository;
    }

    @Override
    public PostDto create(Long boardId, PostDto dto) {
        BoardDto boardDto = this.boardRepository.read(boardId);
        if(boardDto==null) return null;
        else{
            dto.setBoardId(boardId);
            index++;
            dto.setId(index);
            postDtoMap.put(index,dto);
            return dto;
        }
    }

    @Override
    public PostDto read(Long boardId, Long postId) {
        PostDto postDto = postDtoMap.getOrDefault(postId,null);
        if(postDto==null) return null;
        else if(postDto.getBoardId()!=boardId) return null;
        return postDto;
    }

    @Override
    public Collection<PostDto> readAll(Long boardId) {
        if(boardRepository.read(boardId)==null) return null;
        else{
            Collection<PostDto> postDtoList = new ArrayList<>();
            //Key = Postid, Value = Postdto
            postDtoMap.forEach((key, value) -> {
                if(value.getBoardId()==boardId){
                    postDtoList.add(value);
                }
            });
            return postDtoList;
        }
    }

    @Override
    public boolean update(Long boardId, Long postId, PostDto dto) {
        PostDto targetDto = postDtoMap.get(postId);
        if(targetDto==null) return false;
        else if(targetDto.getBoardId()!=boardId) return false;
        else if(!targetDto.getPassword().equals(dto.getPassword())) return false;

        targetDto.setTitle(dto.getTitle() == null ? targetDto.getTitle() : dto.getTitle());
        targetDto.setContent(dto.getContent() == null ? targetDto.getContent() : dto.getContent());

        return true;
    }

    @Override
    public boolean delete(Long boardId, Long postId, String password) {
        PostDto targetDto = postDtoMap.get(postId);
        if(targetDto==null) return false;
        else if(targetDto.getBoardId()!=boardId) return false;
        else if(!targetDto.getPassword().equals(password)) return false;
        postDtoMap.remove(postId);
        return false;
    }
}
