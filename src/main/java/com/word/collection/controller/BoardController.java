package com.word.collection.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.word.collection.config.auth.CustomUserDetails;
import com.word.collection.entity.Board;
import com.word.collection.serivce.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/board/list")
	public String list(Model model, @PageableDefault(size=10, sort="id", direction=Direction.DESC) Pageable pageable,
			@RequestParam(defaultValue = "", required = false) String searchText) {
		
		
		Page<Board> boards = boardService.getBoardListPageAndSearch(pageable, searchText, searchText);
		
		int startPage = Math.max(1, boards.getPageable().getPageNumber() - 5);
		int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 5);
		
		model.addAttribute("boards", boards);
		model.addAttribute("startPage", startPage); // 첫 페이지 수
		model.addAttribute("endPage", endPage); // 끝 페이지 수
		
		return "board/list";
	}
	
	@GetMapping("/board/write")
	public String write() {
		
		return "board/write";
	}
	
	@GetMapping("/board/list/{id}")
	public String detail(Model model, @PathVariable Long id, @AuthenticationPrincipal CustomUserDetails principal) {
		
		Board board = boardService.findById(id);
		
		boardService.visited(board, principal.getUserAccount().getId());
		
		model.addAttribute("board", board);
		model.addAttribute("principal", principal);
		
		return "board/detail";
	}
	
	@GetMapping("/board/modify/{id}")
	public String modify(Model model, @PathVariable Long id) {
		
		Board board = boardService.findById(id);
		
		model.addAttribute("board", board);
		
		return "board/modify";
	}

}
