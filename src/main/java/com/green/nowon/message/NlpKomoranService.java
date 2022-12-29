package com.green.nowon.message;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
	
	public void nlpAnalyze(String strToAnalyze) throws IOException {
		
				
	    KomoranResult analyzeResultList = komoran.analyze(strToAnalyze);

	    System.out.println(analyzeResultList.getPlainText());

	    List<Token> tokenList = analyzeResultList.getTokenList();
	    for (Token token : tokenList) {
	        System.out.format("(%2d, %2d) %s/%s\n", token.getBeginIndex(), token.getEndIndex(), token.getMorph(), token.getPos());
	    }
		
	}

	
	

}
