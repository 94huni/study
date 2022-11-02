package com.study.study.controller;

import com.study.study.entity.Member;
import com.study.study.memberDTO.MemberDTO;
import com.study.study.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/member")
    public String index(){
        return "index";
    }

    @GetMapping("/member/save-form")
    public String saveForm(){
        return "memberPages/saveForm";
    }

    @GetMapping("/member/login-form")
    public String loginForm(){
        return "memberPages/loginForm";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO){
        memberService.save(memberDTO);
        return "memberPages/login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult != null){
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            session.setAttribute("id", loginResult.getId());
            return "memberPages/main";
        }else {
            return "memberPages/login";
        }
    }

    @GetMapping("/member/list")
    public String memberList(Model model){
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);



        return "memberPages/memberList";
    }


}
