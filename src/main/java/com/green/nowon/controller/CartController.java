package com.green.nowon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CartController {
	
	@ResponseBody
	@PostMapping("/user/cart-item")
	public void cartItem(int quantity, long itemNo) {
		System.out.println("구매수량:"+quantity);
		System.out.println("상품번호:"+itemNo);
	}

}
