package com.word.collection.serivce;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.word.collection.entity.UserAccount;
import com.word.collection.entity.Word;
import com.word.collection.entity.enums.WordType;

public interface WordService {
	
	Word findWordId(Long id);
	List<Word> findListAll();
	List<Word> randomWordList();
	List<Word> findWordType(WordType type);
	Page<Word> findByUserId(Pageable pageable, Long userId);
	Page<Word> findByUserIdAndType(Pageable pageable, Long userId, WordType type);
	Page<Word> findByEngContainingOrKorContainingAndUserId(Pageable pageable, String eng, String kor, Long userId);
	Page<Word> findByEngContainingOrKorContainingAndUserIdAndType(Pageable pageable, String eng, String kor, Long userId, WordType type);
	Page<Word> findPageList(Pageable pageable);
	Word add(Word word, UserAccount user);
	Word modify(Word word);
	void delete(Long wordId);

}
