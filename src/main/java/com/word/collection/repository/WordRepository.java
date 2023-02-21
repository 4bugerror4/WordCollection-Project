package com.word.collection.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.word.collection.entity.Word;
import com.word.collection.entity.enums.WordType;

public interface WordRepository extends JpaRepository<Word, Long>{
	
	List<Word> findByType(WordType type);
	List<Word> findByEngContaining(String eng);
	List<Word> findByKorContaining(String kor);
	
	List<Word> findByUserId(Long userId);
	List<Word> findByUserIdAndType(Long userId, WordType type);
	List<Word> findByUserIdAndEngContaining(Long userId, String eng);
	List<Word> findByUserIdAndKorContaining(Long userId, String kor);
	
	List<Word> findByEngContainingAndType(String eng, WordType type);
	List<Word> findByKorContainingAndType(String kor, WordType type);
	
	Page<Word> findByUserId(Pageable pageable, Long userId);
	Page<Word> findByUserIdAndType(Pageable pageable, Long userId, WordType type);
	
	Page<Word> findByUserIdAndEngContaining(Pageable pageable, Long userId, String eng);
	Page<Word> findByUserIdAndKorContaining(Pageable pageable, Long userId, String kor);
	
	Page<Word> findByUserIdAndEngContainingAndType(Pageable pageable, Long userId, String eng, WordType type);
	Page<Word> findByUserIdAndKorContainingAndType(Pageable pageable, Long userId, String kor, WordType type);
	
	@Query(value = "SELECT * FROM word ORDER BY id DESC LIMIT 3", nativeQuery = true)
	List<Word> mFindTopThree();
	
	@Query(value = "SELECT * FROM word WHERE type = :type ORDER BY id DESC LIMIT 3;", nativeQuery = true)
	List<Word> mFindTopThreeType(String type);
	
}
