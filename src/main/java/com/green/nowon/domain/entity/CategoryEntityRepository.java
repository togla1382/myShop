package com.green.nowon.domain.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Long>{

	Optional<CategoryEntity> findByName(String name);
	//1차카테고리 조회
	Optional<CategoryEntity> findByParentNoNullAndName(String name);
	//sub카테고리 조회
	Optional<CategoryEntity> findByParentNoAndName(Long parentNo, String name);
	
	List<CategoryEntity> findByDepthOrderByNameAsc(int depth);
	
	List<CategoryEntity> findByParentNoIsNullOrderByNameAsc();
	List<CategoryEntity> findByParentNoOrderByNameAsc(Long parentNo);

}
