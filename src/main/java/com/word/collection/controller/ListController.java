package com.word.collection.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
public class ListController {
	
	private final WordService wordService;
	
	// Users List
	@GetMapping("/list/users/total_word_list")
	public String userTotalWordList(Model model) {
		
		List<Word> words = wordService.findListAll();
		int wordCount = words.size();
		
		model.addAttribute("words", words);
		model.addAttribute("wordCount", wordCount);
		
		return "/list/users_word/user_total_word_page";
	}
	
	@GetMapping("/list/users/total_noun_list")
	public String userNounWordList(Model model) {
		
		List<Word> words = wordService.findWordType(WordType.noun);
		int wordCount = words.size();
		
		model.addAttribute("words", words);
		model.addAttribute("wordCount", wordCount);
		
		return "/list/users_word/user_noun_word_page";
	}
	
	@GetMapping("/list/users/total_verb_list")
	public String userVerbWordList(Model model) {
		
		List<Word> words = wordService.findWordType(WordType.verb);
		int wordCount = words.size();
		
		model.addAttribute("words", words);
		model.addAttribute("wordCount", wordCount);
		
		return "/list/users_word/user_verb_word_page";
	}
	
	@GetMapping("/list/users/total_adjective_list")
	public String userAdjectiveWordList(Model model) {
		
		List<Word> words = wordService.findWordType(WordType.adjective);
		int wordCount = words.size();
		
		model.addAttribute("words", words);
		model.addAttribute("wordCount", wordCount);
		
		return "/list/users_word/user_adjective_word_page";
	}
	
	// My List
	@GetMapping("/list/my/total_word_list")
	public String myTotalWordList(Model model, @PageableDefault(size=24, sort="id", direction = Direction.DESC) Pageable pageable,
			@RequestParam(defaultValue = "", required = false) String searchText,
			@AuthenticationPrincipal CustomUserDetails principal) {

		Page<Word> words = wordService.findByEngContainingOrKorContainingAndUserId(pageable, searchText, searchText, principal.getUserAccount().getId());
		Long wordCount = words.getTotalElements();
		
		int startPage = Math.max(1, words.getPageable().getPageNumber() - 4);
		int endPage = Math.min(words.getTotalPages(), words.getPageable().getPageNumber() + 4);
		
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("words", words);
		model.addAttribute("wordCount", wordCount);
		
		return "/list/my_word/my_total_word_page";
	}
	
	@GetMapping("/list/my/total_noun_list")
	public String myNounWordList(Model model, @PageableDefault(size=24, sort="id", direction = Direction.DESC) Pageable pageable,
			@RequestParam(defaultValue = "", required = false) String searchText,
			@AuthenticationPrincipal CustomUserDetails principal) {
		
		Page<Word> words = wordService.findByUserIdAndType(pageable, principal.getUserAccount().getId(), WordType.noun);
		Long wordCount = words.getTotalElements();
		
		model.addAttribute("words", words);
		model.addAttribute("wordCount", wordCount);
		
		return "/list/my_word/my_noun_word_page";
	}
	
	@GetMapping("/list/my/total_verb_list")
	public String myVerbWordList(Model model, @PageableDefault(size=24, sort="id", direction = Direction.DESC) Pageable pageable,
			@RequestParam(defaultValue = "", required = false) String searchText,
			@AuthenticationPrincipal CustomUserDetails principal) {
		
		Page<Word> words = wordService.findByUserIdAndType(pageable, principal.getUserAccount().getId(), WordType.verb);
		Long wordCount = words.getTotalElements();
		
		model.addAttribute("words", words);
		model.addAttribute("wordCount", wordCount);
		
		return "/list/my_word/my_verb_word_page";
	}
	
	@GetMapping("/list/my/total_adjective_list")
	public String myAdjectiveWordList(Model model, @PageableDefault(size=24, sort="id", direction = Direction.DESC) Pageable pageable,
			@RequestParam(defaultValue = "", required = false) String searchText,
			@AuthenticationPrincipal CustomUserDetails principal) {
		
		Page<Word> words = wordService.findByUserIdAndType(pageable, principal.getUserAccount().getId(), WordType.adjective);
		Long wordCount = words.getTotalElements();
		
		model.addAttribute("words", words);
		model.addAttribute("wordCount", wordCount);
		
		return "/list/my_word/my_adjective_word_page";
	}

}
