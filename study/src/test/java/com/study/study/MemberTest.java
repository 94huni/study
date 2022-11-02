package com.study.study;

import com.study.study.memberDTO.MemberDTO;
import com.study.study.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberTest {
    @Autowired
    private MemberService memberService;

    public MemberDTO newMember(int i){
        return new MemberDTO("테스트용 이메일"+i, "테스트용 비밀번호"+i, "테스트용이름"+i, 99+i, "테스트용전화번호"+i);
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("회원가입 테스트")
    public void memberSaveTest(){
        int saveId = memberService.save(newMember(1));
        MemberDTO memberDTO =  memberService.findById(saveId);
        assertThat(newMember(1).getMemberEmail()).isEqualTo(memberDTO.getMemberEmail());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("로그인 테스트")
    public void loginTest(){
        final String memberEmail = "로그인용이메일";
        final String memberPassword = "로그인용비밀번호";
        String memberName = "로그인용이름";
        int memberAge = 99;
        String memberMobile = "로그인용 전화번호";

        MemberDTO memberDTO = new MemberDTO(memberEmail, memberPassword, memberName, memberAge, memberMobile);

        int saveId = memberService.save(memberDTO);
        //로그인객체 생성후 로그인
        MemberDTO loginMemberDTO = new MemberDTO();
        loginMemberDTO.setMemberEmail(memberEmail);
        loginMemberDTO.setMemberPassword(memberPassword);

        MemberDTO loginResult = memberService.login(loginMemberDTO);
        //로그인 결과가 not null 이면 테스트 통과
        assertThat(loginResult).isNotNull();

    }

    @Test
    @DisplayName("회원데이터 저장")
    public void memberSave(){
        IntStream.rangeClosed(1,20).forEach(i -> memberService.save(newMember(i)));
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
}
