package com.springboot.Teamproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity(name="board")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;        //게시판 번호

    @Column(nullable = false)
    @NotBlank
    private String title;   //제목

    @Column(nullable = false)
    @NotBlank
    private String content; //내용

    private String imagePath;   //이미지 경로
    
    private String createDate;  //생성 날짜

    @OneToMany(mappedBy = "board")
    @ToString.Exclude
    private List<Comment> commentList;  //댓글 일대다 관계

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User userprofile;   //유저정보 다대일 관계

}
