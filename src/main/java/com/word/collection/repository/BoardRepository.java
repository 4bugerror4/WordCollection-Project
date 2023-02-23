package com.word.collection.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.word.collection.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	Page<Board> findByTitleContainingOrContentContaining(Pageable pageable, String title, String content);
	
}
