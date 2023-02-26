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
import org.springframework.web.bind.annotation.PathVariable;
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
import com.word.collection.entity.enums.WordType;
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

	@GetMapping("/api/word/all/noun")
	public ResponseEntity<?> nounWord() {

		List<Word> words = wordService.findWordType(WordType.noun);

		return new ResponseEntity<>(new CMRespDto<>(1, "전체 명사 출력 완료", words), HttpStatus.OK);
	}

	@GetMapping("/api/word/all/verb")
	public ResponseEntity<?> verbWord() {

		List<Word> words = wordService.findWordType(WordType.verb);

		return new ResponseEntity<>(new CMRespDto<>(1, "전체 동사 출력 완료", words), HttpStatus.OK);
	}

	@GetMapping("/api/word/all/adjective")
	public ResponseEntity<?> adjectiveWord() {

		List<Word> words = wordService.findWordType(WordType.adjective);

		return new ResponseEntity<>(new CMRespDto<>(1, "전체 형용사 출력 완료", words), HttpStatus.OK);
	}

	@GetMapping("/api/word/all/{id}")
	public ResponseEntity<?> myAll(@PathVariable Long id) {

		List<Word> words = wordService.findByUserId(id);

		return new ResponseEntity<>(new CMRespDto<>(1, "나의 단어 출력 완료", words), HttpStatus.OK);
	}
	
	@GetMapping("/api/word/noun/{id}")
	public ResponseEntity<?> myNounAll(@PathVariable Long id) {

		List<Word> words = wordService.findByUserIdAndType(id, WordType.noun);

		return new ResponseEntity<>(new CMRespDto<>(1, "나의 명사 출력 완료", words), HttpStatus.OK);
	}
	
	@GetMapping("/api/word/verb/{id}")
	public ResponseEntity<?> myVerbAll(@PathVariable Long id) {

		List<Word> words = wordService.findByUserIdAndType(id, WordType.verb);

		return new ResponseEntity<>(new CMRespDto<>(1, "나의 동사 출력 완료", words), HttpStatus.OK);
	}
	
	@GetMapping("/api/word/adjective/{id}")
	public ResponseEntity<?> myAdjectiveAll(@PathVariable Long id) {

		List<Word> words = wordService.findByUserIdAndType(id, WordType.adjective);

		return new ResponseEntity<>(new CMRespDto<>(1, "나의 형용사 출력 완료", words), HttpStatus.OK);
	}
	
	@GetMapping("/api/word/{id}")
	public ResponseEntity<?> word(@PathVariable Long id) {

		Word words = wordService.findWordId(id);

		return new ResponseEntity<>(new CMRespDto<>(1, "단어 검색", words), HttpStatus.OK);
	}

	@PostMapping("/api/word/add")
	public ResponseEntity<?> wordAdd(@Valid @RequestBody WordAddDto dto, BindingResult bindingResult,
			@AuthenticationPrincipal CustomUserDetails principal) {

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
