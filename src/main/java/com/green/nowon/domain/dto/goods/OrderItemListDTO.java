package com.green.nowon.domain.dto.goods;

import com.green.nowon.domain.entity.ItemEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
public class OrderItemListDTO {
	private GoodsListDTO item;
	
	private int quantity;
	
	//수량에따른 가격
	private int totPrice;
	
	public OrderItemListDTO quantity(int quantity) {
		this.quantity=quantity;
		this.totPrice=quantity*(item.getPrice()-item.getSPrice());
		return this;
	}
	//주문금액
	public OrderItemListDTO(ItemEntity e){
		this.item=new GoodsListDTO(e);
	}
}
