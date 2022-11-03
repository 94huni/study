package com.springboot.board_login.service;

import com.springboot.board_login.dao.BoardDao;
import com.springboot.board_login.dto.BoardDto;
import com.springboot.board_login.entity.Board;
import com.springboot.board_login.repository.BoardRepository;
import com.springboot.board_login.service.impl.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService implements BoardServiceImpl {

    private final BoardDao boardDao;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    public BoardService(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Override
    public BoardDto getBoard(Long id) {

        Board board = boardDao.selectBoard(id);

        BoardDto boardDto = new BoardDto();
        boardDto.setId(board.getId());
        boardDto.setUsername(board.getUsername());
        boardDto.setContent(board.getContent());

        return boardDto;
    }

    @Override
    public BoardDto writeBoard(BoardDto boardDto) {

        Board board = new Board();
        board.setUsername(boardDto.getUsername());
        board.setContent(boardDto.getContent());
        board.setCreateAt(LocalDateTime.now());
        board.setUpdateAt(LocalDateTime.now());

        Board saveBoard = boardDao.insertBoard(board);
        boardDto.setId(saveBoard.getId());
        boardDto.setUsername(saveBoard.getUsername());
        boardDto.setContent(saveBoard.getContent());


        return boardDto;
    }

    @Override
    public BoardDto modifyBoard(Long id, String username, String content) throws Exception {
        Board modifyBoard = boardDao.modifyBoard(id, username, content);

        BoardDto boardDto = new BoardDto();
        boardDto.setId(modifyBoard.getId());
        boardDto.setUsername(modifyBoard.getUsername());
        boardDto.setContent(modifyBoard.getContent());

        return boardDto;
    }

    @Override
    public void deleteBoard(Long id) throws Exception {

        boardDao.deleteBoard(id);

    }

    @Override
    public List<BoardDto> findAll() {
        List<Board> boardlist = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();
        for(Board board : boardlist){
            BoardDto boardDto = BoardDto.toBoardDto(board);
            boardDtoList.add(BoardDto.toBoardDto(board));
        }

        return boardDtoList;
    }


}
