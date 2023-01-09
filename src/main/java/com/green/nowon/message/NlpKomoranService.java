package com.green.nowon.message;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.green.nowon.domain.entity.ChatBotIntention;
import com.green.nowon.domain.entity.ChatBotIntentionRepository;

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
	
	public String nlpAnalyze(String strToAnalyze) throws IOException {
		
				
	    KomoranResult analyzeResultList = komoran.analyze(strToAnalyze);


	    List<String> tokenList = analyzeResultList.getNouns();
	    String answer=analyzeToken(tokenList);
	    
	    return answer;
		
	}
	public String  analyzeToken(List<String> tokenList) {
		for (String token : tokenList) {	    	
	    	//단어별 1차의도파악
	    	if(decisionTree(token)==null)continue;//다음 단어 확인
	    	
	    	//1차의도에 존재하는 하위의도들
		    for(ChatBotIntention second :repo.findAllByParent_name(token)) {
				//하위의도명과 일치하는 토큰이 있는지 확인
		    	for(String token2 : tokenList) {
		    		if(second.getName().equals(token2)) {
		    			//결정완료!
		    			return second.getAnswer();//일치하는 답변
		    		}
		    	}
			}
	    }
		return repo.findByNameAndParentNull("기타").get().getAnswer();
	}
	
	//1차의도
	public ChatBotIntention decisionTree(String noun) {
		ChatBotIntention intention=repo.findByNameAndParentNull(noun).orElse(null);
		return intention;
	}

	
	

}
