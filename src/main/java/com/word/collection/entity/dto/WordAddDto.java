package com.word.collection.entity.dto;

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
public class WordAddDto {
	
	private String eng;
	private String kor;
	private WordType type;
	
	public Word toEntity() {
		return Word.builder()
				.eng(eng)
				.kor(kor)
				.type(type)
				.build();
	}

}
