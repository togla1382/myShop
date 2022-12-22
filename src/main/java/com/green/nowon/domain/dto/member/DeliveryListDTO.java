package com.green.nowon.domain.dto.member;

import com.green.nowon.domain.entity.DeliveryEntity;

import lombok.Getter;


@Getter
public class DeliveryListDTO {
	
	private long no; 
	private String deliveryAddrName;
	private boolean base;
	private String receiverName;
	private String phone1;
	//private String phone2;
	
	private String postcode;
	private String roadAddress;
	//private String jibunAddress;
	private String detailAddress;
	private String extraAddress;
	
	public DeliveryListDTO(DeliveryEntity e) {
		this.deliveryAddrName = e.getDeliveryAddrName();
		this.base = e.isBase();
		this.receiverName = e.getReceiverName();
		this.phone1 = e.getPhone1();
		this.postcode = e.getPostcode();
		this.roadAddress = e.getRoadAddress();
		this.detailAddress = e.getDetailAddress();
		this.extraAddress = e.getExtraAddress();
	}
	
	

}
