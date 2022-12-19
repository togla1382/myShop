package com.green.nowon.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.goods.GoodsInsertDTO;
import com.green.nowon.domain.dto.goods.GoodsListDTO;
import com.green.nowon.domain.entity.CategoryEntity;
import com.green.nowon.domain.entity.CategoryEntityRepository;
import com.green.nowon.domain.entity.CategoryItemEntity;
import com.green.nowon.domain.entity.CategoryItemEntityRepository;
import com.green.nowon.domain.entity.ItemEntity;
import com.green.nowon.domain.entity.ItemEntityRepository;
import com.green.nowon.domain.entity.ItemListImg;
import com.green.nowon.domain.entity.ItemListImgRepository;
import com.green.nowon.service.GoodsService;
import com.green.nowon.utils.MyFileUtils;

@Service
public class GoodsServiceProcess implements GoodsService {
	
	@Value("${file.location.temp}")
	private String locationTemp;
	
	@Value("${file.location.upload}")
	private String locationUpload;
	
	
	@Autowired
	ItemEntityRepository itemRepo;
	@Autowired
	CategoryItemEntityRepository cateItemRepo;
	@Autowired
	ItemListImgRepository imgRepo;
	@Autowired
	CategoryEntityRepository cateRepo;
	
	@Transactional
	@Override
	public void goodsOfCategory(long cateNo, Model model) {
		//카테고리에 해당하는 상품들모두
		CategoryEntity ca=cateRepo.findById(cateNo).get();
		System.out.println("현재카테고리:"+ca.getName());
		CategoryEntity su=ca.getParent();
		if(su!=null) {
			System.out.println("상위카테고리:"+su.getName());
			su=su.getParent();
			if(su!=null) {
				System.out.println("상위카테고리:"+su.getName());
				su=su.getParent();
				if(su!=null) {
					System.out.println("상위카테고리:"+su.getName());
					su=su.getParent();
				}
			}
		};
		
		
		model.addAttribute("cate", ca);
		model.addAttribute("list", cateItemRepo.findAllByCategoryNo(cateNo)
				.stream()
				.map(GoodsListDTO::new)
				.collect(Collectors.toList()));
	}
	
	@Override
	public Map<String,String> fileTempUpload(MultipartFile gimg) {
		
		return MyFileUtils.fileUpload(gimg, locationTemp);
	}

	@Override
	public void save(GoodsInsertDTO dto){
		//카테고리와 상품 등록
		//이미지 정보 등록, temp->실제 upload위치
		long[] categoryNo=dto.getCategoryNo();
		dto.toItemEntity();
		
		ItemEntity entity=itemRepo.save(dto.toItemEntity());
		for(long no:categoryNo) {
			cateItemRepo.save(CategoryItemEntity.builder()
					.item(entity)
					.category(cateRepo.findById(no).get())
					.build());
		}
		
		dto.toItemListImgs(entity, locationUpload).forEach(imgRepo::save);
		//이미지 temp->temp->실제 upload위치
	}

	
	
	

}
