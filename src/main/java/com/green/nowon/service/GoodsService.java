package com.green.nowon.service;

import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.goods.GoodsInsertDTO;

public interface GoodsService {

	Map<String,String> fileTempUpload(MultipartFile gimg);

	void save(GoodsInsertDTO dto);


	void goodsOfCategory(long cateNo, Model model);

}
