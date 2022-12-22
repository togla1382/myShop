package com.green.nowon.domain.entity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryEntityRepository extends JpaRepository<DeliveryEntity, Long> {

	//객체변수_필드 : Member_email == MemberEmail
	//_ 쿼리메서드에서 객체의내부라는 의미가 있는 토큰이에요 그래서 Entity필드명 만들때 _ 표기법 사용하지 마세요
	long countByMember_email(String email);

	List<DeliveryEntity> findAllByMember_email(String email);

	Optional<DeliveryEntity> findByBaseAndMember_email(boolean base, String email);

}
