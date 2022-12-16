package com.green.nowon.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.green.nowon.domain.entity.MemberEntityRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MyUserDetailsService implements UserDetailsService{

	//DB의 테이블에서 인증처리하기위한 메서드
	@Autowired
	private MemberEntityRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/*
		log.info(">>>>> login page: email -> username" + username);
		//repo.findById(null);
		MemberEntity entity=repo.findByEmailAndSocialAndDeleted(username,false,false)
				.orElseThrow(()->new UsernameNotFoundException("존재하지 않은 이메일") );//NoSuchElementException
		
		//email,pass,roles(Collection<? extends GrantedAuthority> authorities)
		// enum MyRole -> SimpleGrantedAuthority
		 Set<SimpleGrantedAuthority> authorities= entity.getRoles() //Set<MyRole>  --> Set<GrantedAuthority>
				.stream() //Stream<MyRole>
				.map(myRole->new SimpleGrantedAuthority(myRole.getRole()) ) //Stream<GrantedAuthority> "ROLE_USER" or ""
				.collect(Collectors.toSet());
		
		//return new MyUserDetails(username, entity.getPass(), authorities );
		 
		 */
		 return new MyUserDetails(repo.findByEmailAndSocialAndDeleted(username,false,false)
					.orElseThrow(()->new UsernameNotFoundException("존재하지 않은 이메일") ));
	}

}
