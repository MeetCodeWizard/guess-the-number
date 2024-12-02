package com.meetcodewizard.guess_the_number.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("test")
	public String getTestJSP() {
		return "Test";
	}
	
}
