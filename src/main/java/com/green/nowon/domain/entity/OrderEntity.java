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
import javax.persistence.Table;

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
	
	private LocalDateTime orderedDate;//주문일
	
	@Enumerated(EnumType.STRING)
	private OrderStaus status;//주문상태
	
	@JoinColumn//member_mno
	@ManyToOne
	private MemberEntity member; //주문자정보

}
