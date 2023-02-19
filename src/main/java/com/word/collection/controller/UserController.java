package com.word.collection.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.word.collection.config.auth.CustomUserDetails;

@Controller
public class UserController {
	
	@GetMapping("/user/my_profile")
	public String userProfile(@AuthenticationPrincipal CustomUserDetails principal, Model model) {
	
		model.addAttribute("principal", principal);
		
		return "/user/my_profile";
	}
	
	@GetMapping("/user/my_modify")
	public String userModify(@AuthenticationPrincipal CustomUserDetails principal, Model model) {
	
		model.addAttribute("principal", principal);
		
		return "/user/my_modify";
	}
	
	@GetMapping("/user/list")
	public String userList() {
		return "/user/user_list";
	}

}
