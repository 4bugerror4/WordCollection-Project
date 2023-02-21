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
	List<Word> findWordType(WordType type);
	
	Page<Word> findByUserId(Pageable pageable, Long userId);
	Page<Word> findByUserIdAndType(Pageable pageable, Long userId, WordType type);
	
	Page<Word> findPageList(Pageable pageable);
	Word add(Word word, UserAccount user);
	Word modify(Word word);
	void delete(Long wordId);
	
	Page<Word> findByUserIdAndEngContaining(Pageable pageable, Long userId, String eng);
	Page<Word> findByUserIdAndKorContaining(Pageable pageable, Long userId, String kor);
	
	Page<Word> findByUserIdAndEngContainingAndType(Pageable pageable, Long userId, String eng, WordType type);
	Page<Word> findByUserIdAndKorContainingAndType(Pageable pageable, Long userId, String kor, WordType type);
	
	List<Word> mFindTopThree();
	List<Word> mFindTopThreeType(String type);
	
	List<Word> findByEngContaining(String eng);
	List<Word> findByKorContaining(String kor);
	
	List<Word> findByEngContainingAndType(String eng, WordType type);
	List<Word> findByKorContainingAndType(String kor, WordType type);
	
	List<Word> findByUserId(Long userId);
	List<Word> findByUserIdAndEngContaining(Long userId, String eng);
	List<Word> findByUserIdAndKorContaining(Long userId, String kor);
	
	List<Word> findByUserIdAndType(Long userId, WordType type);
}
