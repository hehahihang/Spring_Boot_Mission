package dev.jeongks.curdPractice.service;

import dev.jeongks.curdPractice.dto.BoardDto;

import java.util.Collection;

//실제 비즈니스 로직을 처리할 Service
public interface BoardService {
    BoardDto createBoard(BoardDto boardDto);
    BoardDto readBoard(Long id);
    Collection<BoardDto> readBoardAll();
    boolean updateBoard(Long id,BoardDto boardDto);
    boolean deleteBoard(Long id);
}
