package com.green.nowon.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.domain.dto.goods.CartItemSaveDTO;
import com.green.nowon.security.MyUserDetails;
import com.green.nowon.service.CartService;

@Controller
public class CartController {
	
	@Autowired
	private CartService service;
	
	//인증이후 처리되는 url
	@ResponseBody
	@PostMapping("/user/cart-item")
	public void cartItem(CartItemSaveDTO dto,@AuthenticationPrincipal MyUserDetails myUserDetails ) {
		//System.out.println("이메일:"+myUserDetails.getEmail());
		service.save(dto, myUserDetails.getEmail());
	}

}
