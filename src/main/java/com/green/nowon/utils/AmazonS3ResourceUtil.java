package com.green.nowon.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.core.format.InputAccessor;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Component
public class AmazonS3ResourceUtil {
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	private String tempPath="goods/temp/";
	private String uploadPath="goods/";
	
	private final AmazonS3Client amazonS3Client;
	
	public void store(MultipartFile multipartFile) {
		
		String contentType =multipartFile.getContentType();
		long size= multipartFile.getSize();
		String orgName=multipartFile.getOriginalFilename();
		int idx=orgName.lastIndexOf(".");//파일이름중에서 마직막(.)의 인덱스번호
		String newName=orgName.substring(0, idx)
				+"_"+ (System.nanoTime()/1000000)
				+orgName.substring(idx);// .+확장자 
		
		ObjectMetadata objectMetadata=new ObjectMetadata();
		objectMetadata.setContentType(contentType);
		objectMetadata.setContentLength(size);
		
		
		System.out.println(">>>>>>amazonS3Client: "+amazonS3Client);
		String tempKey=tempPath+newName;
		
		String copyKey=uploadPath+newName;
		
		try (InputStream is=multipartFile.getInputStream()){
			PutObjectRequest putObjectRequest=new PutObjectRequest(bucket, tempKey, is, objectMetadata);
			amazonS3Client.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead));
			
			amazonS3Client.copyObject(this.bucket, tempKey, this.bucket, copyKey);
			
			//DeleteObjectRequest deleteObjectRequest=new DeleteObjectRequest(this.bucket, tempKey);
			amazonS3Client.deleteObject(this.bucket, tempKey);
			
			System.out.println(">>>>> imagePath :"+amazonS3Client.getUrl(bucket, copyKey).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
