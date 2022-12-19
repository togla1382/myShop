package com.green.nowon.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "item")
@Entity
public class ItemEntity extends BaseDateEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	
	private String title;
	private int price;
	private int stock;
	@Lob
	private String content;
	
	
	@Builder.Default
	@OneToMany(mappedBy = "item")
	private List<ItemListImg> imgs=new ArrayList<>();
	
	public ItemListImg defImg() {
		for(ItemListImg img:imgs) {
			if(img.isDefImg()) return img;
				
		}
		return imgs.get(0);//만약에 대표이지미지 없으면 첫번째이미지로
	}
	

}
