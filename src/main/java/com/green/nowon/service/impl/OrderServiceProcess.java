package com.green.nowon.service.impl;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.goods.OrderItemDTO;
import com.green.nowon.domain.dto.goods.OrderItemListDTO;
import com.green.nowon.domain.dto.member.DeliveryInfoDTO;
import com.green.nowon.domain.dto.member.DeliveryListDTO;
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
	
	//회원의 모든 배송지중 기본배송지
	@Override
	public void baseOfdeliveries(String email, Model model) {
		model.addAttribute("base", deliveryRepo.findByBaseAndMember_email(true,email)
				.map(DeliveryListDTO::new)
				.orElseThrow()
				);
		
	}
	//회원의 모든 배송지
	@Override
	public void deliveries(String email, Model model) {
		model.addAttribute("list", deliveryRepo.findAllByMember_email(email).stream()
									.map(DeliveryListDTO::new)
									.collect(Collectors.toList())
				);
	}
	
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
				.base(deliveryRepo.countByMember_email(email)==0?true:false)//배송지정보가 없으면 base=true
				.member(memRepo.findByEmail(email).orElseThrow()));
	}

	

	

	

}
