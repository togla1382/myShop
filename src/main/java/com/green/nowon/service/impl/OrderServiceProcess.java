package com.green.nowon.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.goods.OrderItemDTO;
import com.green.nowon.domain.dto.goods.OrderItemListDTO;
import com.green.nowon.domain.dto.member.DeliveryInfoDTO;
import com.green.nowon.domain.entity.DeliveryEntityRepository;
import com.green.nowon.domain.entity.ItemEntityRepository;
import com.green.nowon.domain.entity.MemberEntityRepository;
import com.green.nowon.service.OrderService;

@Service
public class OrderServiceProcess implements OrderService {

	@Autowired
	private ItemEntityRepository itemRepo;
	
	@Autowired
	private DeliveryEntityRepository deliveryRepo;
	
	@Autowired
	private MemberEntityRepository memRepo;
	
	@Transactional
	@Override
	public void orderItem(OrderItemDTO dto, Model model) {
		model.addAttribute("list", itemRepo.findById(dto.getItemNo()).map(OrderItemListDTO::new)
				.get()
				.quantity(dto.getQuantity()));
	}

	@Override
	public void deliveryInfoSave(DeliveryInfoDTO dto, String email) {
		deliveryRepo.save(dto.toEntity()
				.member(memRepo.findByEmail(email).orElseThrow()));
		
	}

	

}
