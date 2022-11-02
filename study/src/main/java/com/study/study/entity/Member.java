package com.study.study.entity;

import com.study.study.memberDTO.MemberDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, unique = true)
    private String memberEmail;

    @Column(length = 20)
    private String memberPassword;

    @Column(length = 20)
    private String memberName;

    @Column
    private int memberAge;

    @Column(length = 30)
    private String memberMobile;

    public static Member toSaveEntity(MemberDTO memberDTO){
        Member member = new Member();
        member.setMemberEmail(memberDTO.getMemberEmail());
        member.setMemberPassword(memberDTO.getMemberPassword());
        member.setMemberName(memberDTO.getMemberName());
        member.setMemberAge(memberDTO.getMemberAge());
        member.setMemberMobile(memberDTO.getMemberMobile());
        return member;
    }
}
