package dev.jeongks.curdPractice.repository;

import dev.jeongks.curdPractice.dto.BoardDto;

import java.util.Collection;

public interface BoardRespository {
    BoardDto create(BoardDto boardDto);
    BoardDto read(Long id);
    Collection<BoardDto> readAll();
    boolean update(Long id,BoardDto boardDto);
    boolean delete(Long id);
}
