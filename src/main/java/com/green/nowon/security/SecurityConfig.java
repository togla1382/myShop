package com.green.nowon.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
	
	//DB의 인증정보를 이용해서 인증처리하는service 커스터마이징한 빈
	@Bean
	MyUserDetailsService customUserDetailsService() {
		return new MyUserDetailsService();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeRequests(authorize -> authorize
				.antMatchers("/css/**","/js/**","/images/**","/webjars/**","/files/**").permitAll()
				.antMatchers("/","/member/**","/common/**","/kafka/**","/my-websocket/**").permitAll()
				.antMatchers("/user/**").hasRole("USER")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
			.formLogin(formLogin->formLogin
				.loginPage("/member/login")
				.loginProcessingUrl("/member/login")//form태그의 action
				.usernameParameter("email")//username ->email
				.passwordParameter("pass") //password -> pass
				.permitAll()
			)
			.csrf(csrf->csrf.disable())
				
			;
		return http.build();
	}
	
}
