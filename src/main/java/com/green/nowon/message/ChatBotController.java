package com.green.nowon.message;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;



@Controller
public class ChatBotController {

	@Autowired
	NlpKomoranService service;

  @MessageMapping("/hello") // /app/hello
  @SendTo("/topic/greetings")//stompClient.subscribe
  public BotMessage greeting(ClientMessage message) throws Exception {
    Thread.sleep(1000); // simulated delay
    LocalDateTime today=LocalDateTime.now();
    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
    String formattedDay=today.format(formatter);
    String formattedtime=today.format(DateTimeFormatter.ofPattern("a H:mm"));
    
    return new BotMessage(
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
			    	"<div class='menu-item'><span onclick='menuclicked(this)'>상품문의</span></div>"+
			    	"<div class='menu-item'><span onclick='menuclicked(this)'>결제문의</span></div>"+
			    	"<div class='menu-item'><span onclick='menuclicked(this)'>배송문의</span></div>"+
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
  public BotMessage message(ClientMessage message) throws Exception {
    Thread.sleep(1000); // simulated delay
    LocalDateTime today=LocalDateTime.now();
    String formattedtime=today.format(DateTimeFormatter.ofPattern("a H:mm"));
    
    service.nlpAnalyze(message.getContent());
    
    String responseText=message.getContent()+" 대한 답장입니다.";
    
    return new BotMessage(
    "<div class='msg bot flex'>"+
    		
	    "<div class='icon'>"+
    		"<img src='/images/icon/robot-solid.svg' />"+
	    "</div>"+
	    "<div class='message'>"+
		    "<div class='part'>"+
		    	"<p>"+responseText+"</p>"+ 
			 "</div>"+
			 "<div class='time'>"+
			 	formattedtime+
		     "</div>"+
		 "</div>"+
		 
	 "</div>");
  }

}
