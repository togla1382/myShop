package com.green.nowon.message;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;

@Component
public class NlpKomoranService {
	
	
	private String USER_DIC="user.dic";
	private String USER_DIC_PATH="/files/";
	
	private Komoran komoran;
	
	public NlpKomoranService(){
		komoran = new Komoran(DEFAULT_MODEL.LIGHT);
		ClassPathResource cpr=new ClassPathResource("static"+USER_DIC_PATH);
		try {
			komoran.setUserDic(new File(cpr.getFile(),USER_DIC).getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	private ChatBotIntentionRepository repo;
	
	public String nlpAnalyze(ClientMessage message) throws IOException {
		
				
	    KomoranResult analyzeResultList = komoran.analyze(message.getContent());


	    List<String> tokenList = analyzeResultList.getNouns();//입력단어중 명사들만 추출
	    //                          1차토큰,    검색대상
	    String answer=analyzeToken(message.getToken(), tokenList);
	    
	    return answer;
		
	}
	public String  analyzeToken(String selecedToken,List<String> tokenList) {
		if(selecedToken!=null) {
			//1차메뉴에 대한 2차 메뉴 메세지 확인
			return decisionTree(selecedToken, tokenList);
		}
		
		for (String token : tokenList) {	    	
	    	//단어별 1차의도파악
	    	if(decisionTree(token)==null)continue;//다음 단어 확인
	    	
	    	//1차의도에 존재하는 하위의도들
	    	String secondMenu="1"+token+":";
		    for(ChatBotIntention second :repo.findAllByParent_name(token)) {
				//하위의도명과 일치하는 토큰이 있는지 확인
		    	for(String token2 : tokenList) {
		    		if(second.getName().equals(token2)) {
		    			//결정완료!
		    			return "2"+second.getAnswer();//일치하는 답변
		    		}
		    	}
		    	//1차의도만 판별시 2차 메뉴 안내
		    	secondMenu += second.getName()+",";
			}
		    return secondMenu;
	    }
		return "2"+repo.findByNameAndParentNull("기타").get().getAnswer();
	}
	
	//1차의도
	public ChatBotIntention decisionTree(String noun) {
		ChatBotIntention intention=repo.findByNameAndParentNull(noun).orElse(null);
		return intention;
	}
	//2차메뉴에 대한 답변
	public String decisionTree(String intention, List<String> tokenList) {
		System.out.println(">>>>>>>>> 1차선택: "+ intention);
	    for(ChatBotIntention second :repo.findAllByParent_name(intention)) {
			//하위의도명과 일치하는 토큰이 있는지 확인
	    	for(String token2 : tokenList) {
	    		System.out.println("token2:"+ token2);
	    		System.out.println("second.getName():"+ second.getName());
	    		if(second.getName().equals(token2)) {
	    			//결정완료!
	    			System.out.println(">>>2차 선택 응답:"+second.getAnswer());
	    			return "2"+intention+" "+second.getName()+"에 문의에대한 답변입니다</br>"+second.getAnswer();//일치하는 답변
	    		}
	    	}
		}
	    return "2"+repo.findByNameAndParentNull("기타").get().getAnswer();
	}

	
	

}
