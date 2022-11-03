package com.springboot.board_login.service.impl;

import com.springboot.board_login.dto.BoardDto;

import java.util.List;

public interface BoardServiceImpl {

    BoardDto getBoard(Long number);

    BoardDto writeBoard(BoardDto boardDto);

    BoardDto modifyBoard(Long id, String username, String content)throws Exception;

    void deleteBoard(Long id)throws Exception;

    List<BoardDto> findAll();
}
