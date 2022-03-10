package dev.jeongks.missionTwo.Repository;

import dev.jeongks.missionTwo.Dto.BoardDto;

import java.util.Collection;
import java.util.List;

public interface BoardRepository {
    BoardDto create(BoardDto boardDto);
    BoardDto read(Long id);
    Collection<BoardDto> readAll();
    boolean update(Long id, BoardDto boardDto);
    boolean delete(Long id);
}
