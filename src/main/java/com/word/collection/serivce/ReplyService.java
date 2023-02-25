package com.word.collection.serivce;

import com.word.collection.entity.Reply;

public interface ReplyService {
	
	Reply save(Reply reply);
	void delete(Long id);

}
