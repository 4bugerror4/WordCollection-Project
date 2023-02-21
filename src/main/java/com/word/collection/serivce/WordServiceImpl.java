package com.word.collection.serivce;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.word.collection.entity.UserAccount;
import com.word.collection.entity.Word;
import com.word.collection.entity.enums.WordType;
import com.word.collection.repository.WordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {
	
	private final WordRepository wordRepository;
	
	// 단어 등록시 첫글자 대문자, 나머지 소문자 변경
	private static String wordChange(String word) {
		
		String firstLetter = word.substring(0, 1);
		String remainLetter = word.substring(1);
		
		firstLetter = firstLetter.toUpperCase();
		remainLetter = remainLetter.toLowerCase();
		
		return firstLetter + remainLetter;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Word findWordId(Long id) {

		Word word = wordRepository.findById((long) id).orElseThrow(() -> new IllegalArgumentException("해당 번호의 단어는 존재하지 않습니다."));
		
		return word;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Word> findListAll() {
		
		List<Word> words = wordRepository.findAll();
		
		return words;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Word> findWordType(WordType type) {

		List<Word> words = wordRepository.findByType(type);
		
		return words;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Page<Word> findPageList(Pageable pageable) {
		
		Page<Word> words = wordRepository.findAll(pageable);

		return words;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Page<Word> findByUserId(Pageable pageable, Long userId) {

		Page<Word> words = wordRepository.findByUserId(pageable, userId);
		
		return words;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Page<Word> findByUserIdAndType(Pageable pageable, Long userId, WordType type) {

		Page<Word> words = wordRepository.findByUserIdAndType(pageable, userId, type);
		
		return words;
	}


	@Transactional
	@Override
	public Word add(Word word, UserAccount user) {
		
		word.setEng(wordChange(word.getEng()));
		word.setUser(user);
		wordRepository.save(word);
		
		return word;
	}

	@Transactional
	@Override
	public Word modify(Word word) {

		Word wordEntity = wordRepository.findById(word.getId()).orElseThrow(() -> new IllegalArgumentException("해당 번호의 단어는 존재하지 않습니다." + word.getId()));
		
		wordEntity.setEng(wordChange(word.getEng()));
		wordEntity.setKor(word.getKor());
		wordEntity.setType(word.getType());
		
		return wordEntity;
	}

	@Transactional
	@Override
	public void delete(Long wordId) {
		
		wordRepository.deleteById(wordId);
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Word> findByUserIdAndEngContaining(Pageable pageable, Long userId, String eng) {
		
		Page<Word> words = wordRepository.findByUserIdAndEngContaining(pageable, userId, eng);
		
		return words;
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Word> findByUserIdAndKorContaining(Pageable pageable, Long userId, String kor) {
		
		Page<Word> words = wordRepository.findByUserIdAndKorContaining(pageable, userId, kor);
		
		return words;
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Word> findByUserIdAndEngContainingAndType(Pageable pageable, Long userId, String eng, WordType type) {
		
		Page<Word> words = wordRepository.findByUserIdAndEngContainingAndType(pageable, userId, eng, type);
		
		return words;
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Word> findByUserIdAndKorContainingAndType(Pageable pageable, Long userId, String kor, WordType type) {

		Page<Word> words = wordRepository.findByUserIdAndKorContainingAndType(pageable, userId, kor, type);
		
		return words;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Word> mFindTopThree() {

		List<Word> words = wordRepository.mFindTopThree();
		
		return words;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Word> mFindTopThreeType(String type) {

		List<Word> words = wordRepository.mFindTopThreeType(type);
		
		return words;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Word> findByEngContaining(String eng) {

		List<Word> words = wordRepository.findByEngContaining(eng);
		
		return words;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Word> findByKorContaining(String kor) {

		List<Word> words = wordRepository.findByKorContaining(kor);
		
		return words;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Word> findByEngContainingAndType(String eng, WordType type) {

		List<Word> words = wordRepository.findByEngContainingAndType(eng, type);
		
		return words;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Word> findByKorContainingAndType(String kor, WordType type) {

		List<Word> words = wordRepository.findByKorContainingAndType(kor, type);
		
		return words;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Word> findByUserId(Long userId) {

		List<Word> words = wordRepository.findByUserId(userId);
		
		return words;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Word> findByUserIdAndEngContaining(Long userId, String eng) {

		List<Word> words = wordRepository.findByUserIdAndEngContaining(userId, eng);
		
		return words;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Word> findByUserIdAndKorContaining(Long userId, String kor) {

		List<Word> words = wordRepository.findByUserIdAndKorContaining(userId, kor);
		
		return words;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Word> findByUserIdAndType(Long userId, WordType type) {

		List<Word> words = wordRepository.findByUserIdAndType(userId, type);
		
		return words;
	}

}
