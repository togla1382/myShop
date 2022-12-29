package com.green.nowon.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

public class MyFileUtils {
	public static void moveUploadLocationFromTemp(String[] newName, String url){
		ClassPathResource cpr=new ClassPathResource("static"+url+"temp/");
												//"/images/goods/upload/"
		for(String name:newName) {
			try {
				//temp경로의 파일
				File file=new File(cpr.getFile(), name);
				file.renameTo(new File(cpr.getFile().getParent(), name));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	public static Map<String,String> fileUpload(MultipartFile gimg, String location) {
		ClassPathResource cpr=new ClassPathResource("static"+location);
		File folder=null;
		String fileName=null;
		String orgName=null;
		try {
			folder=cpr.getFile();
			orgName=gimg.getOriginalFilename();
			
			int idx=orgName.lastIndexOf(".");//파일이름중에서 마직막(.)의 인덱스번호
			fileName=orgName.substring(0, idx)
					+"_"+ (System.nanoTime()/1000000)
					+orgName.substring(idx);// .+확장자 
			//fileName= UUID.randomUUID().toString()+orgName.substring(idx);
			
			//원본.이름_23847923.jpg
			gimg.transferTo(new File(folder, fileName));
			
			
			System.out.println("임시폴더에 파일업로드: "+location+fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String , String> tempfile=new HashMap<>();
		//tempfile.put("location", location);
		tempfile.put("newName", fileName);
		tempfile.put("orgName", orgName);
		tempfile.put("url", location+fileName);
		return tempfile;
	}
	
	private MyFileUtils() {}

}
