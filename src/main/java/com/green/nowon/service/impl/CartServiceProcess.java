package com.green.nowon.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.goods.CartItemListDTO;
import com.green.nowon.domain.dto.goods.CartItemSaveDTO;
import com.green.nowon.domain.entity.CartEntity;
import com.green.nowon.domain.entity.CartEntityRepository;
import com.green.nowon.domain.entity.CartItemEntity;
import com.green.nowon.domain.entity.CartItemEntityRepository;
import com.green.nowon.domain.entity.ItemEntityRepository;
import com.green.nowon.domain.entity.MemberEntity;
import com.green.nowon.domain.entity.MemberEntityRepository;
import com.green.nowon.service.CartService;

@Service
public class CartServiceProcess implements CartService {

	
	@Autowired
	private CartEntityRepository cartRepo;
	
	@Autowired
	private MemberEntityRepository memRepository;
	
	@Autowired
	private CartItemEntityRepository cartItemRepo;
	@Autowired
	private ItemEntityRepository itemRepo;
	
	@Transactional//이미지 때문에 필요
	@Override
	public void cartItems(Model model, String email) {
		//CartEntity cart= cartRepo.findByMemberEmail(email).get();
		//cartItemRepo.findAllByCartNo(cart.getNo());
		model.addAttribute("list", cartItemRepo.findAllByCartMemberEmail(email)
				.stream()
				.map(CartItemListDTO::new)
				.collect(Collectors.toList()));
	}
	
	@Transactional
	@Override
	public void save(CartItemSaveDTO dto, String email) {
		
		//처음저장시 카드존재하지 않아요
		//MemberEntity member=memRepository.findByEmail(email).orElseThrow();
		
		CartEntity cart= cartRepo.findByMemberEmail(email)
				.orElseGet(()->cartRepo.save(CartEntity.builder()
						.member(memRepository.findByEmail(email).orElseThrow())
						.build()));
		
		
		//System.out.println(">>>>>>>카드가 존재하지않으면 카드생성 됨.");
		
		
		cartItemRepo.findByCartNoAndItemNo(cart.getNo(), dto.getItemNo()).ifPresentOrElse(
					//존재하면 구매수량 증가
					e->e.updateQuantity(dto.getQuantity()),
					//존재하지 않으면 저장
					()->cartItemRepo.save(CartItemEntity.builder()
							.cart(cart)
							.item(itemRepo.findById(dto.getItemNo()).get())
							.quantity(dto.getQuantity())
							.build())
			);
		//만약에 카트에 동일아이템이 이미존재해요
		/*
		Optional<CartItemEntity> result=cartItemRepo.findByCartNoAndItemNo(cart.getNo(), dto.getItemNo());
		if(result.isEmpty()) {
			cartItemRepo.save(CartItemEntity.builder()
					.cart(cart)
					.item(itemRepo.findById(dto.getItemNo()).get())
					.quantity(dto.getQuantity())
					.build());
		}else {
			CartItemEntity changedEntity=result.map(e->e.updateQuantity(dto.getQuantity())).get();
			cartItemRepo.save(changedEntity);
		}
		*/
		
	}


	

}
