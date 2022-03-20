//package dev.jeongks.curdPractice.repository;
//
//import dev.jeongks.curdPractice.dto.BoardDto;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
////DB에 접근하기 위한 DAO를 구현한다.
////2차과제에서는 DB가 없고 DAO에서 직접적으로 가져와야하기 떄문에
////여기에 저장소를 만든다.
//@Repository
//public class BoardDao implements BoardRespository{
//    private static final Logger logger = LoggerFactory.getLogger(BoardDao.class);
//
//
//
//    //JPA 사용하기 전 , 메모리 상에 데이터를 이용하는 방법
//    /*private static Long index = 0L;
//    private Map<Long,BoardDto> meomoryMap = new HashMap<>();
//
//    @Override
//    public BoardDto create(BoardDto boardDto) {
//        index++;
//        boardDto.setId(index);
//        meomoryMap.put(index,boardDto);
//        return boardDto;
//    }
//
//    @Override
//    public BoardDto read(Long id) {
//        return meomoryMap.getOrDefault(id,null);
//    }
//
//    @Override
//    public Collection<BoardDto> readAll() {
//        return meomoryMap.values();
//    }
//
//    @Override
//    public boolean update(Long id, BoardDto boardDto) {
//        if(!meomoryMap.containsKey(id)) return false;
//        boardDto.setId(id);
//        meomoryMap.put(id,boardDto);
//        return true;
//    }
//
//    @Override
//    public boolean delete(Long id) {
//        if(!meomoryMap.containsKey(id)) return false;
//        meomoryMap.remove(id);
//        return true;
//    }*/
//}
