package com.word.collection.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@GetMapping({ "", "/" })
	public String main() {
		return "index";
	}

	@GetMapping("/auth/login")
	public String loginForm(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "exception", required = false) String exception, 
			Model model) {
		
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		
		return "/auth/login";
	}

	@GetMapping("/auth/join")
	public String joinForm() {
		return "/auth/join";
	}

}
