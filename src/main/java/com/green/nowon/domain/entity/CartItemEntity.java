package com.green.nowon.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//@Entity
public class CartItemEntity {
	//같은상품을 여러개 주문할 수 있으므로 개수표현을 위한 entity
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	
	private int count;//수량
	
	@JoinColumn//fk:cart_no
	@ManyToOne
	private CartEntity cart;
	
	@JoinColumn//fk:item_no
	@ManyToOne
	private ItemEntity item;

}
