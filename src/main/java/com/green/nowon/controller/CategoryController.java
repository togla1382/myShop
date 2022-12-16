package com.green.nowon.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.service.CategoryService;
import com.green.nowon.service.impl.CategoryServiceProcess;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping("/admin/categorys")
	public String category() {
		return "category/reg";
	}
	
	@ResponseBody
	@GetMapping("/admin/categorys/{text}")
	public boolean category(@PathVariable String text) {
		return service.isReg(text);
	}
	@GetMapping("/common/layout/categorys/{parentNo}")
	public String category(@PathVariable long parentNo, Model model) {
		service.categoryList(parentNo, model);
		return "category/ol-category";
	}
	
	//admin의 등록페이지
	@GetMapping("/common/categorys")
	public String fistCategory(Model model) {
		service.fistCategory(model);
		return "category/category";
	}
	
	@GetMapping("/common/categorys/{parentNo}")
	public String categoryList(@PathVariable long parentNo,Model model) {
		service.categoryList(parentNo, model);
		return "category/category";
	}
	
	
	@PostMapping("/admin/categorys")
	public String category(String[] name) {
		service.save(name);
		return "category/reg";
	}

}
