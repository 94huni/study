package com.study.study.service;

import com.study.study.entity.Board;
import com.study.study.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;


@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //글작성
    public void write(Board board, MultipartFile file) throws Exception{

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "-" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        board.setFilename(fileName);
        board.setFilepath("/files/"+fileName);

        boardRepository.save(board);
    }
    //게시글리스트처리
    public Page<Board> boardList(Pageable pageable){

        return boardRepository.findAll(pageable);
    }

    //특정게시글
    public Board boardView(Integer id){
        return boardRepository.findById(id).get();
    }

    //게시글삭제
    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }

    //게시글검색
    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable){
        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }





}
