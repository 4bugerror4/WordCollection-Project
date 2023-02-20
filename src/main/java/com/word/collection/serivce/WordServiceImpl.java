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
	public List<Word> randomWordList() {
		
//		// 1. DB에 있는 전체 단어 리스트를 찾는다.
//		List<Word> dbWords = wordRepository.findAll();
//		// 2. 찾아 온 DB의 ID 값 담을 리스트를 생성한다. (단어가 삭제됬을 떄 일치하는 번호가 없을 수 도 있기 때문에)
//		List<Long> dbWordsGetId = new ArrayList<>();
//		
//		// 3. 리스트에 찾아온 단어의 ID 값을 담는다.
//		for (int i = 0; i < dbWords.size(); i++) {
//			dbWordsGetId.add(dbWords.get(i).getId());
//		}
//		
//		// 4. 랜덤 값 담을 리스트 생성
//		List<Integer> randomNum = new ArrayList<>();
//		// 5. 난수 생성을 위한 Random 객체 생성
//		Random r = new Random();
//		// 6. 단어를 담을 리스트 생성
//		List<Word> words = new ArrayList<>();
//		// 7. 내가 추출하고 싶은 갯수 지정
//		final int MAX_WORD = 12;
//		
//		// 8. 랜덤 값 생성 및 해당 랜덤 값으로 단어 리스트 추가
//		for (int i = 0; i < MAX_WORD; i++) {
//			// bound must be positive 에러 DB에 값이 없으면 없을 때는 words를 null로 아무것도 리턴하지 않을 것
//			if (dbWordsGetId.size() <= 0) {
//				
//				words = null;
//				
//			} else {
//				randomNum.add(r.nextInt(dbWordsGetId.size()) + 1);
//				for (int j = 0; j < i; j++) {
//					if (randomNum.get(i) == randomNum.get(j)) {
//						i--;
//					}
//				}
//				
//				words.add(wordRepository.findById(dbWordsGetId.get(randomNum.get(i))).orElseThrow(() -> new IllegalAccessError("해당 번호의 단어는 존재하지 않습니다.")));
//			}
//			
//		}
		
		List<Word> words = null;

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
	
	@Transactional(readOnly = true)
	@Override
	public Page<Word> findByEngContainingOrKorContainingAndUserIdAndType(Pageable pageable, String eng, String kor, Long userId, WordType type) {

		Page<Word> words = wordRepository.findByEngContainingOrKorContainingAndUserIdAndType(pageable, eng, kor, userId, type);
		
		return words;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Page<Word> findByEngContainingOrKorContainingAndUserId(Pageable pageable, String eng, String kor, Long userId) {

		Page<Word> words = wordRepository.findByEngContainingOrKorContainingAndUserId(pageable, eng, kor, userId);
		return words;
	}


	@Transactional
	@Override
	public Word add(Word word, UserAccount user) {

		word.setUser(user);
		wordRepository.save(word);
		
		return word;
	}

	@Transactional
	@Override
	public Word modify(Word word) {

		Word wordEntity = wordRepository.findById(word.getId()).orElseThrow(() -> new IllegalArgumentException("해당 번호의 단어는 존재하지 않습니다." + word.getId()));
		
		wordEntity.setEng(word.getEng());
		wordEntity.setKor(word.getKor());
		wordEntity.setType(word.getType());
		
		return wordEntity;
	}

	@Transactional
	@Override
	public void delete(Long wordId) {
		
		if (wordId == 0 || wordId == null) {
			throw new IllegalArgumentException("해당 번호의 단어는 없습니다.");
		} else {
			wordRepository.deleteById(wordId);
		}
		
	}

}
