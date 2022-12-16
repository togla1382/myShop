package com.green.nowon.domain.dto.goods;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.green.nowon.domain.entity.ItemEntity;
import com.green.nowon.domain.entity.ItemListImg;
import com.green.nowon.utils.MyFileUtils;

import lombok.Data;

@Data
public class GoodsInsertDTO {

	private long[]  categoryNo;
	
	
	private String title;
	private String content;
	private int price;
	private int stock;
	
	
	private String[] newName;
	private String[] orgName;
	
	public List<ItemListImg> toItemListImgs(ItemEntity item,String url) {
		List<ItemListImg> imgs=new ArrayList<>();
		for(int i=0; i<orgName.length; i++) {
			if(orgName[i].equals("") || orgName[i]==null)continue;
			boolean defImg=false;
			if(i==0)defImg=true;
			ItemListImg ent=ItemListImg.builder()
					.url(url)
					.orgName(orgName[i])
					.newName(newName[i])
					.defImg(defImg)
					.item(item)//fk설정을 위한
					.build();
			imgs.add(ent);
		}
		
		//temp 폴더 상위폴더인 upload로 이동
		MyFileUtils.moveUploadLocationFromTemp(newName,url);
		
		return imgs;
	}
	
	public ItemEntity toItemEntity() {
		return ItemEntity.builder()
				.title(title).content(content).price(price).stock(stock)
				.build();
	}
}
