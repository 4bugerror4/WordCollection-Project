package com.word.collection.serivce;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.word.collection.entity.Board;

public interface BoardService {
	
	Board save(Board board);
	Board findById(Long id);
	Board modify(Board board);
	void delete(Long id);
	void visited(Board board, Long principalId);
	Page<Board> getBoardListPageAndSearch(Pageable pageable, String title, String content);

}
