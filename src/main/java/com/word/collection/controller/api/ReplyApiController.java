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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.word.collection.config.auth.CustomUserDetails;
import com.word.collection.entity.Board;
import com.word.collection.entity.Reply;
import com.word.collection.entity.UserAccount;
import com.word.collection.entity.dto.CMRespDto;
import com.word.collection.entity.dto.ReplyAddDto;
import com.word.collection.entity.dto.ReplyDeleteDto;
import com.word.collection.serivce.BoardService;
import com.word.collection.serivce.ReplyService;
import com.word.collection.serivce.UserAccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReplyApiController {
	
	private final BoardService boardService;
	private final UserAccountService userAccountService;
	private final ReplyService replyService;
	
	@PostMapping("/api/reply/save")
	public ResponseEntity<?> save(@Valid @RequestBody ReplyAddDto dto, Errors errors, @AuthenticationPrincipal CustomUserDetails principal) {

		if (errors.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for (FieldError error : errors.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<>(new CMRespDto<>(-1, "유효성 검사 실패", errorMap), HttpStatus.BAD_REQUEST);
		} else {
			Board board = boardService.findById(dto.getBoardId());
			UserAccount user = userAccountService.findById(principal.getUserAccount().getId());
			
			Reply reply = replyService.save(dto.toEntity(board, user));
			
			return new ResponseEntity<>(new CMRespDto<>(1, "댓글 작성 완료", reply), HttpStatus.CREATED);
		}
		
	}
	
	@DeleteMapping("/api/reply/delete")
	public ResponseEntity<?> delete(@RequestBody ReplyDeleteDto dto) {
		
		replyService.delete(dto.getReplyId());
	
		return new ResponseEntity<>(new CMRespDto<>(1, "댓글 삭제 완료", null), HttpStatus.CREATED);
		
	}

}
