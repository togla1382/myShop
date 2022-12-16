package com.green.nowon.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.entity.CategoryEntity;
import com.green.nowon.domain.entity.CategoryEntityRepository;
import com.green.nowon.service.CategoryService;

@Service
public class CategoryServiceProcess implements CategoryService {

	
	@Autowired
	private CategoryEntityRepository repo;
	
	@Override
	public void save(String[] names) {
		//공통점은 Null일때 대체하는 객체를 리턴
		//orElse(): Null값이 아니라도 orElse내부가 실행
		//orElseGet() Null값일때만 orElseGet내부가 실행
		CategoryEntity cate1=repo.findByParentNoAndName(null, names[0])
				.orElseGet(()->repo.save(CategoryEntity.builder().name(names[0]).depth(1).parent(null).build()));
		
		CategoryEntity cate2=repo.findByParentNoAndName(cate1.getNo(), names[1])
				.orElseGet(()->repo.save(CategoryEntity.builder().name(names[1]).depth(2).parent(cate1).build()) );
		
		CategoryEntity cate3=repo.findByParentNoAndName(cate2.getNo(), names[2])
				.orElseGet(()->repo.save(CategoryEntity.builder().name(names[2]).depth(3).parent(cate2).build()) );
		
		CategoryEntity cate4=repo.findByParentNoAndName(cate3.getNo(), names[3])
				.orElseGet(()->repo.save(CategoryEntity.builder().name(names[3]).depth(4).parent(cate3).build()) );
		
		
		
	}

	@Override
	public boolean isReg(String text) {
		Optional<CategoryEntity> result= repo.findByParentNoNullAndName(text);
		if(result.isEmpty())
			return true;
		return false;
	}
	
	//1차카테고리 리스트
	@Override
	public void fistCategory(Model model) {
		//model.addAttribute("list", repo.findByDepthOrderByNameAsc(1));
		//model.addAttribute("list", repo.findByParentNoIsNullOrderByNameAsc());
		model.addAttribute("list", repo.findByParentNoOrderByNameAsc(null));
		
	}

	@Override
	public void categoryList(Long parentNo, Model model) {
		if(parentNo.intValue()==0)parentNo=null;//null 은 1차카테고리
		model.addAttribute("list", repo.findByParentNoOrderByNameAsc(parentNo));
		
	}

}
