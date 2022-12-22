package com.green.nowon.domain.dto.member;

import com.green.nowon.domain.entity.DeliveryEntity;

import lombok.Setter;
import lombok.ToString;


@Setter
public class DeliveryInfoDTO {
	
	private String deliveryAddrName;
	private String receiverName;
	private String phone1;
	private String phone2;
	
	private String postcode;
	private String roadAddress;
	private String jibunAddress;
	private String detailAddress;
	private String extraAddress;
	
	public DeliveryEntity toEntity() {
		return DeliveryEntity.builder()
				.deliveryAddrName(deliveryAddrName)
				.receiverName(receiverName)
				.phone1(phone1)
				.phone2(phone2)
				.postcode(postcode)
				.roadAddress(roadAddress)
				.jibunAddress(jibunAddress)
				.detailAddress(detailAddress)
				.extraAddress(extraAddress)
				.build();
	}

}
