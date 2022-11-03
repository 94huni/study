package com.springboot.board_login.dto;

import com.springboot.board_login.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto {
    private Long id;
    private String username;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public static BoardDto toBoardDto(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setId(Long.valueOf(board.getId()));
        boardDto.setUsername(board.getUsername());
        boardDto.setContent(board.getContent());
        boardDto.setCreateAt(LocalDateTime.now());
        boardDto.setUpdateAt(LocalDateTime.now());
        return boardDto;
    }
}
