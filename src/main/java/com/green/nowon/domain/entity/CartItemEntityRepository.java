package com.green.nowon.domain.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemEntityRepository extends JpaRepository<CartItemEntity, Long>{

	Optional<CartItemEntity> findByCartNoAndItemNo(long careNo, long itemNo);

}
