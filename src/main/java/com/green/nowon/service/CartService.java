package com.green.nowon.service;

import com.green.nowon.domain.dto.goods.CartItemSaveDTO;

public interface CartService {

	void save(CartItemSaveDTO dto, String email);

}
