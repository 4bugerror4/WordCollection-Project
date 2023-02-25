package com.word.collection.serivce;

import org.springframework.stereotype.Service;

import com.word.collection.entity.Reply;
import com.word.collection.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
	
	private final ReplyRepository replyRepository;

	@Override
	public Reply save(Reply reply) {

		Reply replyEntity = replyRepository.save(reply);
		
		return replyEntity;
	}

	@Override
	public void delete(Long id) {

		if (id == null) {
			throw new IllegalArgumentException("해당 번호의 댓글은 없습니다.");
		} else {
			replyRepository.deleteById(id);
		}
		
	}

}
