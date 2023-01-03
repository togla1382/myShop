package com.green.nowon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MyShopApplication {
	
	static {
        System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
    }

	public static void main(String[] args) {
		SpringApplication.run(MyShopApplication.class, args);
	}

}
