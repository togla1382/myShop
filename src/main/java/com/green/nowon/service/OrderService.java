package com.green.nowon.service;

import org.springframework.ui.Model;

import com.green.nowon.domain.dto.goods.OrderItemDTO;
import com.green.nowon.domain.dto.member.DeliveryInfoDTO;
import com.green.nowon.domain.dto.member.OrderInsertDTO;

public interface OrderService {

	void orderItem(OrderItemDTO dto, Model model);

	long deliveryInfoSave(DeliveryInfoDTO dto, String email);

	void deliveries(String email, Model model);

	void baseOfdeliveries(String email, Model model);

	void save(OrderInsertDTO dto, String email);

	

}
