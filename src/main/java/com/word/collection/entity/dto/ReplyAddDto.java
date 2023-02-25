package com.word.collection.entity.dto;

import javax.validation.constraints.NotBlank;

import com.word.collection.entity.Board;
import com.word.collection.entity.Reply;
import com.word.collection.entity.UserAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyAddDto {
	
	private Long boardId;
	
	@NotBlank
	private String comment;
	
	public Reply toEntity(Board board, UserAccount user) {
		return Reply.builder()
				.board(board)
				.comment(comment)
				.user(user)
				.build();
	}

}
