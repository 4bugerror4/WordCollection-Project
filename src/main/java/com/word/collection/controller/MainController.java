package com.word.collection.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.word.collection.entity.Word;
import com.word.collection.serivce.WordService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final WordService wordService;

	@GetMapping({ "", "/" })
	public String main(Model model) {
		
		List<Word> words = wordService.randomWordList();
		
		model.addAttribute("words", words);
		
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
