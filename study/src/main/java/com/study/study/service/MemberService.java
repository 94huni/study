package com.study.study.service;

import com.study.study.entity.Member;
import com.study.study.memberDTO.MemberDTO;
import com.study.study.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    @Autowired

    MemberRepository memberRepository;

    public int save(MemberDTO memberDTO) {
//        memberRepository.save(Member.toSaveEntity(memberDTO));
        Member member = Member.toSaveEntity(memberDTO);
        int saveId =  memberRepository.save(member).getId();
        return saveId;
    }

    public MemberDTO login(MemberDTO memberDTO) {
        /* login에서 이메일 비밀번호 받아오고
         * db로부터 이메일정보가져오고
         * 입력받은 비번과 조회한 비번의 일치여부판단
         * 일치하면 성공 아니면실패
         */
        Optional<Member> optionalMember = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if(optionalMember.isPresent()){
            Member loginEntity = optionalMember.get();
            if(loginEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
                return MemberDTO.toMemberDTO(loginEntity);
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    public MemberDTO findById(int id){
        Optional<Member> optionalMember = memberRepository.findById(id);
        if(optionalMember.isPresent()){
//            return MemberDTO.toMemberDTO(optionalMember.get());
            Member member = optionalMember.get();
            MemberDTO memberDTO = MemberDTO.toMemberDTO(member);
            return memberDTO;
        }else {
            return null;
        }
    }

    public Page<Member> memberList(Pageable pageable){
        return memberRepository.findAll(pageable);
    }

    public List<MemberDTO> findAll() {
        List<Member> memberList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for(Member member : memberList){
            MemberDTO memberDTO = MemberDTO.toMemberDTO(member);
//            memberDTOList.add(memberDTO);
//            memberDTOList.add(memberDTO);
            memberDTOList.add(MemberDTO.toMemberDTO(member));
        }
        return memberDTOList;
    }
}
