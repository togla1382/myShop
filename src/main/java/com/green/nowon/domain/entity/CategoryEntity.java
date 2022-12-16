package com.green.nowon.domain.entity;

import javax.persistence.Entity;
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
@Table(name = "category")
@Entity
public class CategoryEntity {
	//1차~4차
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;//카테고리 번호
	private String name;//카테고리 이름
	private int depth;//차수
	
	@JoinColumn//fk: parent_no
	@ManyToOne
	private CategoryEntity parent; //상위카테고리
	
	

}
