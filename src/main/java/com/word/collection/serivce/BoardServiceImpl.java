package com.word.collection.serivce;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.word.collection.entity.Board;
import com.word.collection.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardRepository boardRepository;

	@Transactional
	@Override
	public Board save(Board board) {

		board.setVisit(0L);
		Board boardEntity = boardRepository.save(board);
		
		return boardEntity;
	}

	@Transactional
	@Override
	public Page<Board> getBoardListPageAndSearch(Pageable pageable, String title, String content) {

		Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(pageable, title, content);
		
		return boards;
	}

	@Transactional(readOnly = true)
	@Override
	public Board findById(Long id) {
		
		Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 번호의 게시글은 없습니다."));
		
		return board;
	}

	@Transactional
	@Override
	public Board modify(Board board) {

		Board boardEntity = boardRepository.findById(board.getId()).orElseThrow(() -> new IllegalAccessError("해당 번호의 게시글은 없습니다."));
		
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		
		return null;
	}

	@Transactional
	@Override
	public void visited(Board board, Long principalId) {
		
		if (board.getUser().getId() != principalId) {
			Long totalVisitCount = board.getVisit();
			totalVisitCount++;
			board.setVisit(totalVisitCount);
		}
	}

	@Transactional
	@Override
	public void delete(Long id) {
		
		if (id == null || id == 0) {
			throw new IllegalArgumentException("해당 번호의 게시글은 없습니다.");
		} else {
			boardRepository.deleteById(id);
		}
		
	}

	

}
