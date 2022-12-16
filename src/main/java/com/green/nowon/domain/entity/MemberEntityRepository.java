package com.green.nowon.domain.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberEntityRepository extends JpaRepository<MemberEntity, Long>{

	//쿼리 메서드 유형 : 문법에 맞지않으면 오류발생
	Optional<MemberEntity> findByEmailAndSocialAndDeleted(String username, boolean b, boolean c);

}
