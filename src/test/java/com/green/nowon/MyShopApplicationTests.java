package com.green.nowon;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.nowon.domain.entity.MemberEntity;
import com.green.nowon.domain.entity.MemberEntityRepository;
import com.green.nowon.message.ChatBotIntention;
import com.green.nowon.message.ChatBotIntentionRepository;
import com.green.nowon.security.MyRole;

@SpringBootTest
class MyShopApplicationTests {

	@Autowired
	MemberEntityRepository mrepo;
	@Autowired
	ChatBotIntentionRepository reop;
	@Autowired
	PasswordEncoder pe;
	
	//@Test
	void 의도0() {
		reop.save(ChatBotIntention.builder()
				.name("기타")
				.answer("죄송합니다! 아직 답변이 준비되지 않았습니다. 현재 상품, 결제, 배송 관련 질문만 답변가능합니다.")
				.build());
	}
	//@Test
	void 의도() {
		reop.save(ChatBotIntention.builder()
				.name("상품")
				.build());
		reop.save(ChatBotIntention.builder()
				.name("결제")
				.build());
		reop.save(ChatBotIntention.builder()
				.name("배송")
				.build());
	}
	
	//@Test
	void 의도2() {
		
		reop.save(ChatBotIntention.builder()
				.name("재고")
				.answer("해당하는 상품명을 입력해주시면 재고를 안내 해 드리겠습니다.")
				.parent(reop.findByName("상품").get())
				.build());
		reop.save(ChatBotIntention.builder()
				.name("주문")
				.answer("주문방법은 상품상세 페이지에서 구매수량을 확인하고 구매하기 버튼을 클릭하면 주문이 가능합니다.")
				.parent(reop.findByName("상품").get())
				.build());
		reop.save(ChatBotIntention.builder()
				.name("판매자")
				.answer("상품의 판매자는 CODD와 협약된 가맹점에서 판매하는 상품입니다. 해당하는 상품의 이름이나 번호를 입력하세면 판매자 정보를 안내해 드리겠습니다.")
				.parent(reop.findByName("상품").get())
				.build());
		
	}
	//@Test
	void 의도3() {
		
		reop.save(ChatBotIntention.builder()
				.name("방법")
				.answer("현재 결제시스템은 카드 인증으로 가능합니다.")
				.parent(reop.findByName("결제").get())
				.build());
		reop.save(ChatBotIntention.builder()
				.name("취소")
				.answer("마이페이지>구매내역 에서 취소가능 상태(배송전 상품)의 상품은 취소 가능합니다.")
				.parent(reop.findByName("결제").get())
				.build());
		reop.save(ChatBotIntention.builder()
				.name("오류")
				.answer("오류관련해서는 1588-0000 고객센터로 문의하시면 친절히 안내해 드리겠습니다.")
				.parent(reop.findByName("결제").get())
				.build());
		
	}
	
	//@Test
	void 의도4() {
		
		reop.save(ChatBotIntention.builder()
				.name("택배")
				.answer("택배사는 현재 판매자에 따라 배송사가 다르며 판매자에 문의 하거나 상품 상페페이지를 참조하시기 바랍니다.")
				.parent(reop.findByName("배송").get())
				.build());
		reop.save(ChatBotIntention.builder()
				.name("조회")
				.answer("마이페이지>구매내역 에서 배송번호를 클릭하시면 조회가 가능합니다. 자세한 조회는 각 택배사 홈페이지를 이용하시면 가능합니다.")
				.parent(reop.findByName("배송").get())
				.build());
		
	}
	
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
