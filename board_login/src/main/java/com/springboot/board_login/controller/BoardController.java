package com.springboot.board_login.controller;

import com.springboot.board_login.dto.BoardDto;
import com.springboot.board_login.entity.Board;
import com.springboot.board_login.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public String boardList(Model model){
        List<BoardDto> list = boardService.findAll();
        model.addAttribute("list", list);
        return "board/list";
    }

    @GetMapping("/view")
    public String viewBoard(Model model, Long id){

        model.addAttribute("board", boardService.getBoard(id));
        return "board/view";

    }

    @GetMapping("/write")
    public String writeBoard(){
        return "board/write";
    }

    @GetMapping("/insert")
    public String insertBoard(BoardDto boardDto, Model model)throws Exception{

        boardService.writeBoard(boardDto);

        return "board/list";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Long id, Model model){
        model.addAttribute("board", boardService.getBoard(id));

        return "board/modify";
    }

    @PostMapping("/update/{id}")
    public String updateBoard(@PathVariable("id") Long id, BoardDto boardDto, Model model){

        BoardDto boardTemp = boardService.getBoard(id);

        boardTemp.setUsername(boardDto.getUsername());
        boardTemp.setContent(boardDto.getContent());

        boardService.writeBoard(boardTemp);

        return "redirect:/board/list";
    }

    @GetMapping("/board/delete")
    public String deleteBoard(Long id)throws Exception{
        boardService.deleteBoard(id);
        return "redirect:/board/list";
    }




}
