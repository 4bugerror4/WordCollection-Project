package com.word.collection.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.word.collection.config.auth.CustomUserDetails;
import com.word.collection.entity.Word;
import com.word.collection.entity.dto.CMRespDto;
import com.word.collection.entity.dto.WordAddDto;
import com.word.collection.entity.dto.WordDeleteDto;
import com.word.collection.entity.dto.WordModifyDto;
import com.word.collection.serivce.WordService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WordApiController {
	
	private final WordService wordService;
	
	@GetMapping("/api/word/all")
	public ResponseEntity<?> wordAll() {
		
		List<Word> words = wordService.findListAll();
		
		return new ResponseEntity<>(new CMRespDto<>(1, "전체 단어 출력 완료", words), HttpStatus.OK); 
	}
	
	@PostMapping("/api/word/add")
	public ResponseEntity<?> wordAdd(@Valid @RequestBody WordAddDto dto, BindingResult bindingResult, @AuthenticationPrincipal CustomUserDetails principal) {
		
		if (bindingResult.hasErrors()) {
			
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}

			return new ResponseEntity<>(new CMRespDto<>(-1, "단어 등록 실패", errorMap), HttpStatus.BAD_REQUEST);
			
		} else {
			Word word = wordService.add(dto.toEntity(), principal.getUserAccount());
			
			return new ResponseEntity<>(new CMRespDto<>(1, "단어 등록 완료", word), HttpStatus.CREATED);
		}
		
	}
	
	
	@PutMapping("/api/word/modify")
	public ResponseEntity<?> wordModify(@Valid @RequestBody WordModifyDto dto, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<>(new CMRespDto<>(-1, "단어 수정 실패", errorMap), HttpStatus.BAD_REQUEST);
		} else {
			Word word = wordService.modify(dto.toEntity());
			
			return new ResponseEntity<>(new CMRespDto<>(1, "단어 수정 완료", word), HttpStatus.OK);
		}
		
	}
	
	@DeleteMapping("/api/word/delete")
	public ResponseEntity<?> wordDelete(@RequestBody WordDeleteDto dto) {
		
		wordService.delete(dto.getId());
		
		return new ResponseEntity<>(new CMRespDto<>(1, "단어 삭제 완료", null), HttpStatus.OK);
	}
}
