package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CreateAccountController {

	@GetMapping("/CreateAccount")
	public String CreateAccount() {
		System.out.println("This is working.");
		return "CreateAccount";
	}
}