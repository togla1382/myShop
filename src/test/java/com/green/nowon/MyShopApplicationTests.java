package com.green.nowon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.nowon.domain.entity.MemberEntity;
import com.green.nowon.domain.entity.MemberEntityRepository;
import com.green.nowon.security.MyRole;

@SpringBootTest
class MyShopApplicationTests {

	@Autowired
	MemberEntityRepository mrepo;
	
	
	@Autowired
	PasswordEncoder pe;
	//@Test
	void 어드민계정() {
		
		mrepo.save(
				MemberEntity.builder()
				.email("admin@test.com")
				.pass(pe.encode("1234"))
				.name("관리자").nickName("관리자")
				.build()//엔티티생성
				.addRole(MyRole.USER)
				.addRole(MyRole.ADMIN)
				);
	}
	
	
	

}
