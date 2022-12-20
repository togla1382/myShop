package com.green.nowon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@ResponseBody
	@GetMapping("/member/login-check")
	public boolean loginCheck(Authentication auth) {
		//로그인했을때는 인증정보확인가능
		//비로그인시 는 null
		return auth==null? false:true;
	}

}
