package com.green.nowon.domain.dto.goods;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.green.nowon.domain.entity.CategoryItemEntity;
import com.green.nowon.domain.entity.ItemEntity;
import com.green.nowon.domain.entity.ItemListImg;
import com.green.nowon.utils.MyFileUtils;

import lombok.Data;

@Data
public class GoodsListDTO {

	private long no;
	private String title;
	private int price;
	private int stock;
	
	
	
	//이미지 대표이미지
	private String defImgUrl;

	public GoodsListDTO(ItemEntity e) {
		this.no = e.getNo();
		this.title = e.getTitle();
		this.price = e.getPrice();
		this.stock = e.getStock();
		this.defImgUrl = e.defImg().getUrl()+e.defImg().getNewName();
		
	}
	public GoodsListDTO(CategoryItemEntity cie) {
		this(cie.getItem());
	}
	
	
}
