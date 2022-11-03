package com.springboot.board_login.dao;

import com.springboot.board_login.controller.Controller;
import com.springboot.board_login.dao.Impl.BoardDaoImpl;
import com.springboot.board_login.entity.Board;
import com.springboot.board_login.repository.BoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
@Component
public class BoardDao implements BoardDaoImpl {

    BoardRepository boardRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @Autowired
    public BoardDao(BoardRepository boardRepository){
        this.boardRepository=boardRepository;
    }

    @Override
    public Board insertBoard(Board board) {

        LOGGER.info("saveBoard");
        Board saveBoard = boardRepository.save(board);

        return saveBoard;
    }

    @Override
    public Board selectBoard(Long id) {

        LOGGER.info("selectBoard");
        Board selectBoard = boardRepository.getById(id);

        return selectBoard;
    }

    @Override
    public Board modifyBoard(Long id, String username, String content) throws Exception {

        Optional<Board> selectBoard = boardRepository.findById(id);

        Board modifyBoard;
        if(selectBoard.isPresent()){
            Board board = selectBoard.get();

            board.setUsername(username);
            board.setContent(content);
            board.setUpdateAt(LocalDateTime.now());

            modifyBoard = boardRepository.save(board);
        }else {
            throw new Exception();
        }

        return modifyBoard;
    }

    @Override
    public void deleteBoard(Long id) throws Exception {

        Optional<Board> selectBoard = boardRepository.findById(id);

        if(selectBoard.isPresent()){
            Board board = selectBoard.get();

            boardRepository.delete(board);
        }else {
            throw new Exception();
        }

    }
}
