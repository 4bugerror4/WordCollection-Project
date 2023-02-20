package com.word.collection.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.word.collection.entity.Word;
import com.word.collection.entity.enums.WordType;

public interface WordRepository extends JpaRepository<Word, Long>{
	
	List<Word> findByType(WordType type);
	Page<Word> findByUserId(Pageable pageable, Long userId);
	Page<Word> findByUserIdAndType(Pageable pageable, Long userId, WordType type);
	Page<Word> findByEngContainingOrKorContainingAndUserId(Pageable pageable, String eng, String kor, Long userId);
	Page<Word> findByEngContainingOrKorContainingAndUserIdAndType(Pageable pageable, String eng, String kor, Long userId, WordType type);
	
}
