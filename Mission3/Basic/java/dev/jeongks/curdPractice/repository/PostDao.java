//package dev.jeongks.curdPractice.repository;
//
//import dev.jeongks.curdPractice.dto.BoardDto;
//import dev.jeongks.curdPractice.dto.PostDto;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
//@Repository
//public class PostDao implements PostRepository{
//    private static final Logger logger = LoggerFactory.getLogger(PostDao.class);
//
//    //Post관련 DB에 접근할때 Board도 접근해야 하므로 boardRepository 접근
//    private BoardRespository boardRespository;
//    private Map<Long,PostDto> memory = new HashMap<>();
//    private static Long index = 0L;
//
//    public PostDao(
//            @Autowired BoardRespository boardRespository
//    ){
//        this.boardRespository = boardRespository;
//    }
//
//    @Override
//    public PostDto create(Long boardId, PostDto postDto) {
//        BoardDto boardDto = this.boardRespository.read(boardId);
//        if(boardDto==null) return null;
//        else{
//            boardDto.setId(boardId);
//            index++;
//            postDto.setId(index);
//            memory.put(index,postDto);
//            return postDto;
//        }
//    }
//
//    @Override
//    public PostDto read(Long boardId, Long postId) {
//        PostDto postDto = memory.getOrDefault(postId,null);
//        if(postDto==null) return null;
//        else if(postDto.getBoardId()!=boardId) return null;
//        else return postDto;
//    }
//
//    @Override
//    public Collection<PostDto> readAll(Long boardId) {
//        if(this.boardRespository.read(boardId)==null) return null;
//        else{
//            Collection<PostDto> postDtoCollection = new ArrayList<>();
//            memory.forEach((key,value)-> {
//                if(value.getBoardId()==boardId){
//                    postDtoCollection.add(value);
//                }
//            });
//            return postDtoCollection;
//        }
//    }
//
//    @Override
//    public boolean update(Long boardId, Long postId, PostDto postDto) {
//        //update를 할려면 뭘 알아야할까를 생각해보자
//        PostDto targetPost = memory.get(postId);
//        if(targetPost==null) return false;
//        else if(targetPost.getBoardId()!=boardId) return false;
//        else if(!Objects.equals(targetPost.getPassword(),postDto.getPassword())) return false;
//
//        targetPost.setTitle(postDto.getTitle()==null ? targetPost.getTitle() : postDto.getTitle() );
//        targetPost.setContent(postDto.getContent()==null ? targetPost.getContent() : postDto.getContent());
//        return true;
//    }
//
//    @Override
//    public boolean delete(Long boardId, Long postId,String password) {
//        PostDto targetPost = memory.get(postId);
//        if(targetPost==null) return false;
//        else if(targetPost.getBoardId()!=boardId) return false;
//        else if(!targetPost.getPassword().equals(password)) return false;
//        memory.remove(postId);
//        return true;
//    }
//}
