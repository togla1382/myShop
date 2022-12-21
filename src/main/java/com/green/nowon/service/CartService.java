package com.green.nowon.service;

import org.springframework.ui.Model;

import com.green.nowon.domain.dto.goods.CartItemSaveDTO;

public interface CartService {

	void save(CartItemSaveDTO dto, String email);

	void cartItems(Model model, String email);

}
