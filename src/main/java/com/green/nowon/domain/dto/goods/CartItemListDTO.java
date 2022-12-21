package com.green.nowon.domain.dto.goods;

import com.green.nowon.domain.entity.CartItemEntity;

import lombok.Data;
import lombok.Getter;

@Getter
public class CartItemListDTO {
	
	private long no;//cartItem번호
	private int quantity;//구매수량
	
	//배송비
	private int dPrice;
	
	//할인정보
	private int sPrice;
	
	//item 정보 기존 item dto활용
	private GoodsListDTO item;

	public CartItemListDTO(CartItemEntity e) {
		this.no = e.getNo();
		this.quantity = e.getQuantity();
		this.item = new GoodsListDTO(e.getItem());
		this.dPrice=3000;
		this.sPrice=0;
	}
	
}
