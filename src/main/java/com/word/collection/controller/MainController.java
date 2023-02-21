package com.word.collection.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.word.collection.config.auth.CustomUserDetails;
import com.word.collection.entity.Word;
import com.word.collection.entity.enums.WordType;
import com.word.collection.serivce.WordService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final WordService wordService;

	@GetMapping({ "", "/" })
	public String main(Model model, @AuthenticationPrincipal CustomUserDetails principal) {
		
		List<Word> totalWordTopThree = wordService.mFindTopThree();
		List<Word> totalNounTopThreeType = wordService.mFindTopThreeType("noun");
		List<Word> totalVerbTopThreeType = wordService.mFindTopThreeType("verb");
		List<Word> totalAdjectiveTopThreeType = wordService.mFindTopThreeType("adjective");
		
		model.addAttribute("total", totalWordTopThree);
		model.addAttribute("noun", totalNounTopThreeType);
		model.addAttribute("verb", totalVerbTopThreeType);
		model.addAttribute("adjective", totalAdjectiveTopThreeType);
		
		List<Word> wordTotal = wordService.findListAll();
		List<Word> wordNoun = wordService.findWordType(WordType.noun);
		List<Word> wordVerb = wordService.findWordType(WordType.verb);
		List<Word> wordAdjective = wordService.findWordType(WordType.adjective);

		model.addAttribute("totalCount", wordTotal.size());
		model.addAttribute("nounCount", wordNoun.size());
		model.addAttribute("verbCount", wordVerb.size());
		model.addAttribute("adjectiveCount", wordAdjective.size());
		
		if (principal != null) {
			List<Word> totalMyWord = wordService.findByUserId(principal.getUserAccount().getId());
			List<Word> totalMyNoun = wordService.findByUserIdAndType(principal.getUserAccount().getId(), WordType.noun);
			List<Word> totalMyVerb = wordService.findByUserIdAndType(principal.getUserAccount().getId(), WordType.verb);
			List<Word> totalMyAdjective = wordService.findByUserIdAndType(principal.getUserAccount().getId(), WordType.adjective);
			
			model.addAttribute("myWordCount", totalMyWord.size());
			model.addAttribute("myNounCount", totalMyNoun.size());
			model.addAttribute("myVerbCount", totalMyVerb.size());
			model.addAttribute("myAdjectiveCount", totalMyAdjective.size());
		}
		
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
