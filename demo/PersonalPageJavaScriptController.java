package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonalPageJavaScriptController {

	@GetMapping("/PersonalPageJavaScript.js")
	public String PersonalPageJavaScript() {
		return "PersonalPageJavaScript.js";
	}
}