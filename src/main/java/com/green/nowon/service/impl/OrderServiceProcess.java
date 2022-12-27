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
import com.green.nowon.domain.dto.member.OrderInsertDTO;
import com.green.nowon.domain.dto.member.OrderItemInsertDTO;
import com.green.nowon.domain.entity.DeliveryEntity;
import com.green.nowon.domain.entity.DeliveryEntityRepository;
import com.green.nowon.domain.entity.ItemEntity;
import com.green.nowon.domain.entity.ItemEntityRepository;
import com.green.nowon.domain.entity.MemberEntityRepository;
import com.green.nowon.domain.entity.OrderEntity;
import com.green.nowon.domain.entity.OrderEntityRepository;
import com.green.nowon.domain.entity.OrderItemEntity;
import com.green.nowon.domain.entity.OrderItemEntityRepository;
import com.green.nowon.domain.entity.OrderStaus;
import com.green.nowon.service.OrderService;

@Service
public class OrderServiceProcess implements OrderService {

	@Autowired
	private ItemEntityRepository itemRepo;
	
	@Autowired
	private DeliveryEntityRepository deliveryRepo;
	
	@Autowired
	private MemberEntityRepository memRepo;
	
	@Autowired
	private OrderEntityRepository orderRepo;
	@Autowired
	private OrderItemEntityRepository orderItemRepo;
	
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
	public long deliveryInfoSave(DeliveryInfoDTO dto, String email) {
		return deliveryRepo.save(dto.toEntity()
				.base(deliveryRepo.countByMember_email(email)==0?true:false)//배송지정보가 없으면 base=true
				.member(memRepo.findByEmail(email).orElseThrow()))
				.getNo();//배송지 정보등록 완료후 pk값리턴
	}
	
	//주문완료후 결제정보 DB저장
	@Override
	public void save(OrderInsertDTO dto, String email) {
		
		//주문
		OrderEntity orderResult=orderRepo.save(OrderEntity.builder()
				.status(OrderStaus.ORDER)
				.paymentNo(dto.getPaymentNo())
				.member(memRepo.findByEmail(email).orElseThrow())
				//.delivery(DeliveryEntity.builder().no(dto.getDeliveryNo()).build())
				.delivery(deliveryRepo.findById(dto.getDeliveryNo()).orElseThrow())
				.build());
		
		//orderItem
		//아이템 재고 주문수량만큼 감소
		for(OrderItemInsertDTO oiDto : dto.getOrderItems()) {
			orderItemRepo.save(OrderItemEntity.builder()
					.quantity(oiDto.getQuantity())
					.item(ItemEntity.builder().no(oiDto.getItemNo()).build())
					.order(orderResult)
					.build());
		}
		
		
		
	}

	

	

	

}
