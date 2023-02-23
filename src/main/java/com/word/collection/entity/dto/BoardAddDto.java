package com.word.collection.entity.dto;

import javax.validation.constraints.NotBlank;

import com.word.collection.entity.Board;
import com.word.collection.entity.UserAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardAddDto {
	
	@NotBlank
	private String title;
	
	private String content;
	
	public Board toEntity(UserAccount user) {
		return Board.builder()
				.title(title)
				.content(content)
				.user(user)
				.build();
	}

}
