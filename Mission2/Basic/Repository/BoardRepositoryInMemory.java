package dev.jeongks.missionTwo.Repository;

import dev.jeongks.missionTwo.Dto.BoardDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;

//DB에 접근하는 Repository Interface를 구현한 클래스
@Repository
public class BoardRepositoryInMemory implements BoardRepository{
    private static final Logger logger = LoggerFactory.getLogger(BoardRepositoryInMemory.class);
    private static Long index = 0L;
    Map<Long,BoardDto> boardDtoMap = new HashMap<>();

    @Override
    public BoardDto create(BoardDto boardDto) {
        index++;
        boardDto.setId(index);
        boardDtoMap.put(index,boardDto);
        return boardDtoMap.get(index);
    }

    @Override
    public BoardDto read(Long id) {
        //값이 있으면 가져오고 아니면 NULL RETURN
        return boardDtoMap.getOrDefault(id,null);
    }

    @Override
    public Collection<BoardDto> readAll() {
        return boardDtoMap.values(); //map value들 출력 BoardDto를 Collection 형태로 내보냄
    }

    @Override
    public boolean update(Long id, BoardDto boardDto) {
        if(boardDtoMap.containsKey(id)){
            boardDto.setId(id);
            boardDtoMap.put(id,boardDto);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if(boardDtoMap.containsKey(id)){
            boardDtoMap.remove(id);
            return true;
        }
        return false;
    }
}
