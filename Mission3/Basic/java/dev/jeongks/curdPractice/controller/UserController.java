package dev.jeongks.curdPractice.controller;

import dev.jeongks.curdPractice.dto.BoardDto;
import dev.jeongks.curdPractice.dto.UserDto;
import dev.jeongks.curdPractice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    public UserController(
            @Autowired UserService userService
    ){
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserDto> createUser(
            @RequestBody UserDto dto
    ){
        this.userService.createUser(dto);
        return ResponseEntity.ok(null);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> readUser(
            @PathVariable("id") Long id
    ){
        if(this.userService.readUser(id)==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(this.userService.readUser(id));
    }

    @GetMapping()
    public ResponseEntity<Collection<UserDto>> readUserAll(){
        return ResponseEntity.ok(this.userService.readUserAll());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable("id") Long id,
            @RequestBody UserDto dto
    ){
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(
            @PathVariable("id") Long id
    ){
        return ResponseEntity.ok(null);
    }
}
