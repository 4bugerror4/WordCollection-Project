package com.word.collection.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.FileSystemUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.word.collection.config.auth.CustomUserDetails;
import com.word.collection.entity.UserAccount;
import com.word.collection.entity.dto.CMRespDto;
import com.word.collection.entity.dto.UserAccountJoinDto;
import com.word.collection.entity.dto.UserAccountModifyDto;
import com.word.collection.serivce.UserAccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserAccountApiController {

	private final UserAccountService userAccountService;

	@PostMapping("/api/user/join")
	public ResponseEntity<?> userJoin(@Valid @RequestBody UserAccountJoinDto dto, BindingResult bindingResult) {
		
		userAccountService.checkUsernameDuplicate(dto.toEntity());
		userAccountService.checkNicknameDuplicate(dto.toEntity());
		userAccountService.checkEmailDuplicate(dto.toEntity());

		if (bindingResult.hasErrors()) {

			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}

			return new ResponseEntity<>(new CMRespDto<>(-1, "회원가입 실패", errorMap), HttpStatus.BAD_REQUEST);

		} else {
			UserAccount user = userAccountService.joinUser(dto.toEntity());

			return new ResponseEntity<>(new CMRespDto<>(1, "회원가입 성공", user), HttpStatus.CREATED);
		}

	}
	
	@PutMapping("/api/user/modify")
	public ResponseEntity<?> userModify(@Valid @RequestPart(value = "dto", required = false) UserAccountModifyDto dto,
			BindingResult bindingResult,
			@RequestPart(value = "file", required = false) MultipartFile file,
			@AuthenticationPrincipal CustomUserDetails principal) {
		
		if (!principal.getUserAccount().getNickname().equals(dto.getNickname())) {
			userAccountService.checkNicknameDuplicate(dto.toEntity());
		}
		
		if (!principal.getUserAccount().getEmail().equals(dto.getEmail())) {
			userAccountService.checkEmailDuplicate(dto.toEntity());
		}
		
		if (bindingResult.hasErrors()) {

			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}

			return new ResponseEntity<>(new CMRespDto<>(-1, "회원가입 실패", errorMap), HttpStatus.BAD_REQUEST);

		} else {
			
			UserAccount user =  userAccountService.modifyUser(dto.toEntity(), file);
			
			principal.setUserAccount(user);
			
			return new ResponseEntity<>(new CMRespDto<>(1, "회원수정 성공", user), HttpStatus.CREATED);
		}
		
	}

}
