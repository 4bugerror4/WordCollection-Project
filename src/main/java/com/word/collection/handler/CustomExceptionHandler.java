package com.word.collection.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.word.collection.entity.dto.CMRespDto;
import com.word.collection.handler.ex.CustomUserCheckApiException;

@RestController
@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(CustomUserCheckApiException.class)
	public ResponseEntity<?> checkUserApiValidation(CustomUserCheckApiException e) {
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
	}

}
