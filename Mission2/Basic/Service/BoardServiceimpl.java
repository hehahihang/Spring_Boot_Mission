package dev.jeongks.missionTwo.Service;

import dev.jeongks.missionTwo.Dto.BoardDto;
import dev.jeongks.missionTwo.Repository.BoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

//Service 는 비즈니스 로직만을 처리
//Service는 DB에 조회해서 데이터를 받아와야 하니까
//@Autowired를 통해 DB에 접근하는 BoardRepository 연결
@Service
public class BoardServiceimpl implements BoardService{
    private static final Logger logger = LoggerFactory.getLogger(BoardServiceimpl.class);
    private BoardRepository boardRepository;

    public BoardServiceimpl(
            @Autowired BoardRepository boardRepository
    ){
        this.boardRepository = boardRepository;
    }
    
    //실제 로직을 처리한다고 생각
    @Override
    public BoardDto createBoard(BoardDto boardDto) {
        //Board를 생성하려면 DB에 create 해야하지?
        //DB 접근하는 boardRepository에 create하는 메소드를 실행
        //그리고 레퍼런스 boardDto를 전달
        return this.boardRepository.create(boardDto);
    }

    @Override
    public BoardDto read(Long id) {
        //특정 아이디 값을 가지는 Board 하나를 읽는 것
        return this.boardRepository.read(id);
    }

    @Override
    public Collection<BoardDto> readAll() {
        return this.boardRepository.readAll();
    }

    //비즈니스 로직만 처리하면 되니까. void로 가는거지
    //DB접근이 필요하니까 DB역할을 하는 boardRepository에 updateBoard에서 얻은 정보를 그대로 가지고
    //boardRepository에 접근
    @Override
    public boolean updateBoard(Long id, BoardDto boardDto) {
        return this.boardRepository.update(id,boardDto);
    }

    @Override
    public boolean deleteBoard(Long id) {
        return this.boardRepository.delete(id);
    }
}
