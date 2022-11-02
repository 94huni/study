package com.study.study.memberDTO;

import com.study.study.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private int memberAge;
    private String memberMobile;

    public MemberDTO(String memberEmail, String memberPassword, String memberName, int memberAge, String memberMobile) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberAge = memberAge;
        this.memberMobile = memberMobile;
    }

    public static MemberDTO toMemberDTO(Member member){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(Long.valueOf(member.getId()));
        memberDTO.setMemberEmail(member.getMemberEmail());
        memberDTO.setMemberPassword(member.getMemberPassword());
        memberDTO.setMemberName(member.getMemberName());
        memberDTO.setMemberAge(member.getMemberAge());
        memberDTO.setMemberMobile(member.getMemberMobile());
        return memberDTO;

    }

}
