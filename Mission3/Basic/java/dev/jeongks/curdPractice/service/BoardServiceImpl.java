package dev.jeongks.curdPractice.service;

import dev.jeongks.curdPractice.dto.BoardDto;
import dev.jeongks.curdPractice.entity.BoardEntity;
import dev.jeongks.curdPractice.repository.BoardRespository;
import dev.jeongks.curdPractice.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService{
    //비즈니스 로직 처리를 위해 repositoriy 연결해 사용
    private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
    private final BoardRespository boardRespository;

    public BoardServiceImpl(
            @Autowired BoardRespository boardRespository
    ){
        this.boardRespository = boardRespository;
    }

    @Override
    public BoardDto createBoard(BoardDto boardDto) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setName(boardDto.getName());
        boardEntity = this.boardRespository.save(boardEntity);
        BoardDto newBoard = new BoardDto();
        newBoard.setId(boardEntity.getId());
        newBoard.setName(boardEntity.getName());
        return newBoard;
    }

    @Override
    public BoardDto readBoard(Long id) {
        Optional<BoardEntity> optionalBoardEntity = this.boardRespository.findById(id);
        BoardEntity boardEntity = optionalBoardEntity.get();
        BoardDto targetBoard = new BoardDto();
        targetBoard.setId(boardEntity.getId());
        targetBoard.setName(boardEntity.getName());
        return targetBoard;
    }

    @Override
    public Collection<BoardDto> readBoardAll() {
        Iterable<BoardEntity> boardEntityIterable = this.boardRespository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();
        for(BoardEntity boardEntity : boardEntityIterable){
            boardDtoList.add(new BoardDto(
                    boardEntity.getId(),
                    boardEntity.getName()
            ));
        }
        return boardDtoList;
    }

    @Override
    public boolean updateBoard(Long id, BoardDto boardDto) {
        Optional<BoardEntity> optionalBoardEntity = this.boardRespository.findById(id);
        if(optionalBoardEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        BoardEntity boardEntity = optionalBoardEntity.get();
        boardEntity.setName(boardDto.getName()!=null ? boardDto.getName() : boardEntity.getName());
        this.boardRespository.save(boardEntity);
        return true;
    }

    @Override
    public boolean deleteBoard(Long id) {
        Optional<BoardEntity> optionalBoardEntity = this.boardRespository.findById(id);
        BoardEntity boardEntity = optionalBoardEntity.get();
        if(optionalBoardEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        this.boardRespository.delete(boardEntity);
        return true;
    }
}
