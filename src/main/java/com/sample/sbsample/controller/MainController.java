package com.sample.sbsample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
    public String hello() {
    	System.out.println("호이호이호이 들어왔습니까?");
        return "hello";
    }
}
