package com.word.collection.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.word.collection.config.auth.CustomUserDetails;
import com.word.collection.entity.Board;
import com.word.collection.entity.dto.BoardAddDto;
import com.word.collection.entity.dto.BoardDeleteDto;
import com.word.collection.entity.dto.BoardModifyDto;
import com.word.collection.entity.dto.CMRespDto;
import com.word.collection.serivce.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardApiController {
	
	private final BoardService boardService;
	
	@GetMapping("/api/boards")
	public ResponseEntity<?> boards() {
		
		List<Board> boards = boardService.findAll();
		
		return new ResponseEntity<>(new CMRespDto<>(1, "게시판 목록 전체 리스트", boards), HttpStatus.OK);
	}
	
	@GetMapping("/api/board/{id}")
	public ResponseEntity<?> boards(@PathVariable Long id) {
		
		Board board = boardService.findById(id);
		
		return new ResponseEntity<>(new CMRespDto<>(1, "게시판 상세보기", board), HttpStatus.OK);
	}
	
	@GetMapping("/api/board/search")
	public ResponseEntity<?> searchBoard(@RequestParam(value = "", required = false) String searchText) {
		
		List<Board> boards = boardService.findByTitleContainingOrContentContaining(searchText, searchText);
		
		return new ResponseEntity<>(new CMRespDto<>(1, "게시판 글 검색 리스트", boards), HttpStatus.OK);
	}
	
	
	@PostMapping("/api/board/save")
	public ResponseEntity<?> save(@Valid @RequestBody BoardAddDto dto, Errors errors, @AuthenticationPrincipal CustomUserDetails principal) {
		
		if (errors.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for (FieldError error : errors.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<>(new CMRespDto<>(-1, "유효성 검사 실패", errorMap), HttpStatus.BAD_REQUEST);
			
		} else {
			
			Board board = boardService.save(dto.toEntity(principal.getUserAccount()));
			
			return new ResponseEntity<>(new CMRespDto<>(1, "게시판 글 작성 완료", board), HttpStatus.CREATED);
		}
		
	}
	
	@PutMapping("/api/board/modify")
	public ResponseEntity<?> modify(@Valid @RequestBody BoardModifyDto dto, Errors errors) {
		
		if (errors.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for (FieldError error : errors.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<>(new CMRespDto<>(-1, "유효성 검사 실패", errorMap), HttpStatus.BAD_REQUEST);
			
		} else {
			
			Board board = boardService.modify(dto.toEntity());
			
			return new ResponseEntity<>(new CMRespDto<>(1, "게시글 수정 완료", board), HttpStatus.OK);
		}
		
	}
	
	@DeleteMapping("/api/board/delete")
	public ResponseEntity<?> delete(@RequestBody BoardDeleteDto dto) {
		
		boardService.delete(dto.getId());
		
		return new ResponseEntity<>(new CMRespDto<>(1, "게시글 삭제 완료", null), HttpStatus.OK);
	}

}
