package com.springboot.board_login.controller;

import com.springboot.board_login.dto.BoardDto;
import com.springboot.board_login.entity.Board;
import com.springboot.board_login.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    BoardService boardService;

    @GetMapping("/")
    public String list(Board board){
        List<BoardDto> boardDto = boardService.findAll();

        return boardDto.toString();
    }

    @GetMapping("/get")
    public BoardDto get(Long id){
        BoardDto boardGet = boardService.getBoard(id);
        return boardGet;
    }

    @PostMapping("/write")
    public String write(@RequestBody BoardDto boardDto){
        return boardDto.toString();
    }

    @PutMapping("/update")
    public String update(@RequestBody BoardDto boardDto){
        return boardDto.toString();
    }

    @PutMapping("/update1")
    public ResponseEntity<BoardDto> post(@RequestBody BoardDto boardDto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(boardDto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        return id;
    }

}
