package dev.jeongks.curdPractice.repository;

import dev.jeongks.curdPractice.dto.BoardDto;
import dev.jeongks.curdPractice.entity.BoardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface BoardRespository extends CrudRepository<BoardEntity,Long> {
//    BoardDto create(BoardDto boardDto);
//    BoardDto read(Long id);
//    Collection<BoardDto> readAll();
//    boolean update(Long id,BoardDto boardDto);
//    boolean delete(Long id);
}
