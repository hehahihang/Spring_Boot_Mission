package dev.jeongks.missionTwo.Controller;

import dev.jeongks.missionTwo.Dto.BoardDto;
import dev.jeongks.missionTwo.Service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//1. View에서 전송한 정보를 Controller에서 수신. 받은 정보를 Service에게 넘김.
//2. Service에서 핵심 비즈니스 로직을 수행 후 DB접근이 필요하면 Repository에게 요청

@RestController
@RequestMapping("board")
public class BoardController {
    private final static Logger logger = LoggerFactory.getLogger(BoardController.class);
    private BoardService boardService;

    //BoardContoller가 생성될 때 비즈니스 로직을 처리하는 BoardService 객체 의존성 주입 및 @Autowired연결
    public BoardController(
            @Autowired BoardService boardService
    ){
        this.boardService = boardService;
    }

    
    //API를 만드는거 잖아.
    //그러면 뭘 보여줘야 하나
    //ResponseEntity 사용?
    //Status Filed를 가진다 +
    @PostMapping
    public ResponseEntity<BoardDto> createBoard(@RequestBody BoardDto boardDto){
        logger.info("Create Post");
        return ResponseEntity.ok(boardService.createBoard(boardDto));
    }

    @GetMapping("{id}")
    public ResponseEntity readBoard(
            @PathVariable("id") Long id
    ){
        logger.info("Board id {} read",id);
        BoardDto boardDto = this.boardService.read(id);
        if(boardDto==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(this.boardService.read(id));
    }

    @GetMapping
    public ResponseEntity readBoardAll(){
        logger.info("Board Read all");
        return ResponseEntity.ok(this.boardService.readAll());
    }

    @PutMapping("{id}")
    public ResponseEntity updateBoard(
            @PathVariable("id") Long id,
            @RequestBody BoardDto boardDto
    ){
        if(!boardService.updateBoard(id,boardDto)){
            //update할 ID가 없는 경우
            logger.info("update Board fail");
            return ResponseEntity.notFound().build();
        }
        logger.info("update Board success");
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteBoard(
            @PathVariable("id") Long id
    ){
        if(!this.boardService.deleteBoard(id)){
            logger.info("delete Board fail");
            return ResponseEntity.notFound().build();
        }
        logger.info("delete Board success");
        return ResponseEntity.noContent().build();
    }
}
