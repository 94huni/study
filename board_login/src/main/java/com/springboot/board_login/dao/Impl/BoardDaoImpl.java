package com.springboot.board_login.dao.Impl;

import com.springboot.board_login.entity.Board;

public interface BoardDaoImpl {

    Board insertBoard(Board board);

    Board selectBoard(Long id);

    Board modifyBoard(Long id, String username, String content)throws Exception;

    void deleteBoard(Long id) throws Exception;
}
