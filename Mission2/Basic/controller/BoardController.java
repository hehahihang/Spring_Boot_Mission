package dev.jeongks.curdPractice.controller;

import dev.jeongks.curdPractice.dto.BoardDto;
import dev.jeongks.curdPractice.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("board")
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    private BoardService boardService;

    public BoardController(
            @Autowired BoardService boardService
    ){
        this.boardService = boardService;
    }

//    @PostMapping
//    public BoardDto createBoard(
//            @RequestBody BoardDto boardDto
//    ){
//        logger.info("Board is created!");
//        return this.boardService.createBoard(boardDto);
//    }
//
//    @GetMapping("{id}")
//    public BoardDto readBoard(
//            @PathVariable("id") Long id
//    ){
//        return this.boardService.readBoard(id);
//    }
//
//    @GetMapping()
//    public Collection<BoardDto> readBoardAll(){
//        logger.info("All board is read");
//        return this.boardService.readBoardAll();
//    }
//
//    @PutMapping("{id}")
//    public boolean updateBoard(
//            @PathVariable("id") Long id,
//            @RequestBody BoardDto boardDto
//    ){
//        return this.boardService.updateBoard(id,boardDto);
//    }
//
//    @DeleteMapping("{id}")
//    public boolean deleteBoard(
//            @PathVariable("id") Long id
//    ){
//        return this.boardService.deleteBoard(id);
//    }


    @PostMapping
    public ResponseEntity<BoardDto> createBoard(
            @RequestBody BoardDto boardDto
    ){
        return ResponseEntity.ok(this.boardService.createBoard(boardDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<BoardDto> readBoard(
            @PathVariable("id") Long id
    ){
        return ResponseEntity.ok(this.boardService.readBoard(id));
    }

    @GetMapping()
    public ResponseEntity<?> readBoardAll(){
        return ResponseEntity.ok(this.boardService.readBoardAll());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateBoard(
            @PathVariable("id") Long id,
            @RequestBody BoardDto boardDto
    ){
        if(this.boardService.updateBoard(id,boardDto)){
            logger.info("id : {} board is updated {} ",id,boardDto);
            BoardDto updatedBoard = this.boardService.readBoard(id);
            return ResponseEntity.ok(updatedBoard);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBoard(
            @PathVariable("id") Long id
    ){
        if(this.boardService.deleteBoard(id)){
            logger.info("id : {} board is deleted",id);
            return ResponseEntity.noContent().build();
        }
        else
            return ResponseEntity.notFound().build();
    }

}
