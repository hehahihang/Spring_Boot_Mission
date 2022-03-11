package dev.jeongks.missionTwo.Service;

import dev.jeongks.missionTwo.Dto.BoardDto;

import java.util.Collection;
import java.util.List;

public interface BoardService {
    BoardDto createBoard(BoardDto boardDto);
    BoardDto read(Long id);
    Collection<BoardDto> readAll();
    boolean updateBoard(Long id, BoardDto boardDto);
    boolean deleteBoard(Long id);
}
