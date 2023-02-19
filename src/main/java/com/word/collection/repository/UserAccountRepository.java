package com.word.collection.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.word.collection.entity.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
	
	Optional<UserAccount> findByUsername(String username);
	boolean existsByUsername(String username);
	boolean existsByNickname(String nickname);
	boolean existsByEmail(String email);

}
