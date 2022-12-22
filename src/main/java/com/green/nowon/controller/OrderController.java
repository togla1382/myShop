package com.green.nowon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.domain.dto.goods.OrderItemDTO;
import com.green.nowon.domain.dto.member.DeliveryInfoDTO;
import com.green.nowon.security.MyUserDetails;
import com.green.nowon.service.OrderService;


@Controller
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@ResponseBody
	@PostMapping("/user/delivery")
	public void deliveryInfo(DeliveryInfoDTO dto, @AuthenticationPrincipal MyUserDetails userDetails) {
		service.deliveryInfoSave(dto, userDetails.getEmail());
	}
	
	@GetMapping("/user/order")
	public String orderPayment(OrderItemDTO dto, Model model) {
		service.orderItem(dto, model);
		return "user/order-payment";
	}
	
	//@ResponseBody 표기하지 않은 ajax요청입니다. response결과로 HTML페이지
	@GetMapping("/user/deliveries")
	public String deliveries(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {
		service.deliveries(userDetails.getEmail(), model);
		return "user/deliveries";
	}
	
	//@ResponseBody 표기하지 않은 ajax요청입니다. response결과로 HTML페이지
		@GetMapping("/user/deliveries/base")
		public String baseOfdeliveries(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {
			service.baseOfdeliveries(userDetails.getEmail(), model);
			return "user/deliveries-base";
		}
	
	

}
