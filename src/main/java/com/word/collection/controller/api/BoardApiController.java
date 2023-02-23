package com.word.collection.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
			
			boardService.modify(dto.toEntity());
			
			return new ResponseEntity<>(new CMRespDto<>(1, "게시글 수정 완료", null), HttpStatus.OK);
		}
		
	}
	
	@DeleteMapping("/api/board/delete")
	public ResponseEntity<?> delete(@RequestBody BoardDeleteDto dto) {
		
		boardService.delete(dto.getId());
		
		return new ResponseEntity<>(new CMRespDto<>(1, "게시글 삭제 완료", null), HttpStatus.OK);
	}

}
