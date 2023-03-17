package com.sample.sbsample.controller;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {

	@GetMapping("signin")
	public <T> ResponseEntity<T> compCheck(HashMap<String,String> paramasMap){		
		System.out.println("######################");
		System.out.println("신난다 아주 좋아!!!!!!!!!");
		System.out.println("######################");
		return null;
	}
	
	@GetMapping("sssss")
	public <T> ResponseEntity<T> sssss(HashMap<String,String> paramasMap){		
		System.out.println("######################");
		System.out.println("신난다 sssssssssssssss 아주 좋아!!!!!!!!!");
		System.out.println("######################");
		return null;
	}
}
