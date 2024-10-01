package com.kamal.SocialMedia.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String HomeControllerHandler() {
		return "This is home controller";
	}
	
	@GetMapping("/home")
	public String HomeControllerHandler2() {
		return "This is another home controller";
	}

}
