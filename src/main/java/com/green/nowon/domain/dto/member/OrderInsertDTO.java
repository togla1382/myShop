package com.green.nowon.domain.dto.member;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class OrderInsertDTO{

	private List<OrderItemInsertDTO> orderItems;
	private String paymentNo;
	private long deliveryNo;
}
