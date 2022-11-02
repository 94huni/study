package com.study.study.repository;

import com.study.study.entity.Board;
import com.study.study.entity.Member;
import com.study.study.memberDTO.MemberDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    //select * from member where memberEmail =?
    //리턴타입 : Member
    //매개변수 : memberEmail(String)
    Optional<Member> findByMemberEmail(String memberEmail);

    Page<Member> findByMemberEmailContaining(String searchKeyword, Pageable pageable);

}
