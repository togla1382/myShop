package com.green.nowon.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "my_order")
@Entity
public class OrderEntity extends BaseDateEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;//주문번호
	
	@CreationTimestamp
	private LocalDateTime orderedDate;//주문일
	
	@Enumerated(EnumType.STRING)
	private OrderStaus status;//주문상태
	
	private String paymentNo;//merchant_uid
	
	@JoinColumn//member_mno
	@ManyToOne
	private MemberEntity member; //주문자정보
	
	@JoinColumn
	@OneToOne
	private DeliveryEntity delivery;

}
