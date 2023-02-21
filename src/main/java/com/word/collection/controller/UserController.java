package com.word.collection.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.word.collection.config.auth.CustomUserDetails;
import com.word.collection.entity.UserAccount;
import com.word.collection.entity.Word;
import com.word.collection.serivce.UserAccountService;
import com.word.collection.serivce.WordService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	private final WordService wordService;
	private final UserAccountService userAccountService;
	
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
	
	@GetMapping("/list/my/total_word_modify_delete_list")
	public String modifyDelete(Model model, @PageableDefault(size=24, sort="id", direction = Direction.DESC) Pageable pageable,
			@RequestParam(defaultValue = "", required = false) String searchText,
			@RequestParam(defaultValue = "", required = false) String searchType,
			@AuthenticationPrincipal CustomUserDetails principal) {
		
		List<Word> words = null;
		
		if (searchType.equals("eng")) {
			
			words = wordService.findByUserIdAndEngContaining(principal.getUserAccount().getId(), searchText);
			
		} else if (searchType.equals("kor")) {
			
			words = wordService.findByUserIdAndKorContaining(principal.getUserAccount().getId(), searchText);
			
		} else {
			words = wordService.findByUserId(principal.getUserAccount().getId());
		}
		
		int wordCount = words.size();
		
		model.addAttribute("words", words);
		model.addAttribute("wordCount", wordCount);
		
		return "/user/my_word_modify_delete";
	}
	
	@GetMapping("/user/list")
	public String userList(Model model) {
		
		List<UserAccount> users = userAccountService.findUserAll();
		System.out.println(users.toString());
		model.addAttribute("users", users);
		
		return "/user/user_list";
	}
	
}
