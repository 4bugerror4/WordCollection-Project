package com.word.collection.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListController {
	
	// Users List
	@GetMapping("/list/users/total_word_list")
	public String userTotalWordList() {
		return "/list/users_word/user_total_word_page";
	}
	
	@GetMapping("/list/users/total_noun_list")
	public String userNounWordList() {
		return "/list/users_word/user_noun_word_page";
	}
	
	@GetMapping("/list/users/total_verb_list")
	public String userVerbWordList() {
		return "/list/users_word/user_verb_word_page";
	}
	
	@GetMapping("/list/users/total_adjective_list")
	public String userAdjectiveWordList() {
		return "/list/users_word/user_adjective_word_page";
	}
	
	// My List
	@GetMapping("/list/my/total_word_list")
	public String myTotalWordList() {
		return "/list/my_word/my_total_word_page";
	}
	
	@GetMapping("/list/my/total_noun_list")
	public String myNounWordList() {
		return "/list/my_word/my_noun_word_page";
	}
	
	@GetMapping("/list/my/total_verb_list")
	public String myVerbWordList() {
		return "/list/my_word/my_verb_word_page";
	}
	
	@GetMapping("/list/my/total_adjective_list")
	public String myAdjectiveWordList() {
		return "/list/my_word/my_adjective_word_page";
	}

}
