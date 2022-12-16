package com.green.nowon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.nowon.domain.dto.member.MemberInsertDTO;
import com.green.nowon.service.LogService;
import com.green.nowon.service.impl.LogServiceProcess;

@Controller
public class LogController {
	
	@Autowired
	private LogService service;
	
	@GetMapping("/member/login")
	public String login() {
		return "sign/signin";
	}
	
	@GetMapping("/member/signup")
	public String join() {
		return "sign/signup";
	}
	
	//회원가입처리
	@PostMapping("/member/signup")
	public String join(MemberInsertDTO dto) {
		service.save(dto);
		return "redirect:/member/login";//회원가입후 로그인페이지로
	}

}
