package com.word.collection.entity.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.word.collection.entity.Word;
import com.word.collection.entity.enums.WordType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WordModifyDto {
	
	private Long id;
	
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]*$", message = "영어만 들어 올 수 있습니다.")
	private String eng;
	
	@NotBlank
	@Pattern(regexp = "^[ㄱ-ㅎ가-힣]*$", message = "한글만 들어 올 수 있습니다.")
	private String kor;
	
	private WordType type;
	
	public Word toEntity() {
		return Word.builder()
				.id(id)
				.eng(eng)
				.kor(kor)
				.type(type)
				.build();
	}

}
