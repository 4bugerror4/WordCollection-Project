package com.word.collection.entity.dto;

import javax.validation.constraints.NotBlank;

import com.word.collection.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardModifyDto {
	
	private Long id;
	
	@NotBlank
	private String title;
	
	private String content;
	
	public Board toEntity() {
		return Board.builder()
				.id(id)
				.title(title)
				.content(content)
				.build();
	}

}
