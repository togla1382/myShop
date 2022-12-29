package com.green.nowon.message;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;



@Controller
public class ChatBotController {


  @MessageMapping("/hello") // /app/hello
  @SendTo("/topic/greetings")//stompClient.subscribe
  public Greeting greeting(HelloMessage message) throws Exception {
    Thread.sleep(1000); // simulated delay
    LocalDateTime today=LocalDateTime.now();
    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
    String formattedDay=today.format(formatter);
    String formattedtime=today.format(DateTimeFormatter.ofPattern("a H:mm"));
    
    return new Greeting(
    "<div class='flex center date' >"+formattedDay+"</div>"+
    "<div class='msg bot flex'>"+
    		
	    "<div class='icon'>"+
    		"<img src='/images/icon/robot-solid.svg' />"+
	    "</div>"+
	    "<div class='message'>"+
		    "<div class='part'>"+
		    		"<p>안녕하세요, CODD클라우드봇이에요. 궁금한 점은 언제든지 저에게 물어보세요!</p>"+
		    "</div>" +   
		    "<div class='part'>"+
		    	"<p>아래는 많은 분들이 궁금해하신 내용이에요</p>"+ 
			    "<div class='flex center menu'>"+ 
			    	"<div class='menu-item flex row center'><span>상품재고</span></div>"+
			    	"<div class='menu-item'><span>결제문의</span></div>"+
			    	"<div class='menu-item'><span>배송문의</span></div>"+
			    "</div>"+
			 "</div>"+
			 "<div class='time'>"+
			 	formattedtime+
		     "</div>"+
		 "</div>"+
		 
	 "</div>");
  }
  
  @MessageMapping("/message")
  @SendTo("/topic/message")//stompClient.subscribe
  public Greeting message(HelloMessage message) throws Exception {
    Thread.sleep(1000); // simulated delay
    return new Greeting("메세지수신: " + HtmlUtils.htmlEscape(message.getContent()) );
  }

}
