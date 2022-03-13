package dev.jeongks.curdPractice.service;

import dev.jeongks.curdPractice.dto.BoardDto;
import dev.jeongks.curdPractice.repository.BoardRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BoardServiceImpl implements BoardService{
    //비즈니스 로직 처리를 위해 repositoriy 연결해 사용
    private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
    private BoardRespository boardRespository;
    public BoardServiceImpl(
            @Autowired BoardRespository boardRespository
    ){
        this.boardRespository = boardRespository;
    }

    @Override
    public BoardDto createBoard(BoardDto boardDto) {
        return this.boardRespository.create(boardDto);
    }

    @Override
    public BoardDto readBoard(Long id) {
        return this.boardRespository.read(id);
    }

    @Override
    public Collection<BoardDto> readBoardAll() {
        return this.boardRespository.readAll();
    }

    @Override
    public boolean updateBoard(Long id, BoardDto boardDto) {
        return this.boardRespository.update(id,boardDto);
    }

    @Override
    public boolean deleteBoard(Long id) {
        return this.boardRespository.delete(id);
    }
}
