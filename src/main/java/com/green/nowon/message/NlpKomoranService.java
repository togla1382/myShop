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
	    String answer=repo.findByNameAndParentNull("기타").get().getAnswer();
	    //System.out.println(answer);
	    
	    for (String noun : tokenList) {
	    	
	    	//1차의도파악
	    	List<ChatBotIntention> re=repo.findByParent(repo.findByNameAndParentNull(noun).orElse(null));
	    	
	    	if(re!=null) {
	    		//2차의도파악
	    		for(ChatBotIntention e:re) {
	    			for(String noun2 : tokenList) {
	    				System.out.println(e.getName()+":"+noun2);
	    				if(e.getName().equals(noun2))answer=e.getAnswer();
	    			}
	    		}
	    	}
	    	
	    }
	    return answer;
		
	}

	
	

}
