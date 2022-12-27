package com.green.nowon.domain.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemInsertDTO {
	private long itemNo;
	private int quantity;
}
