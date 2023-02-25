package com.word.collection.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.word.collection.entity.enums.GenderType;
import com.word.collection.entity.enums.GradeType;
import com.word.collection.entity.enums.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserAccount extends BaseTime {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, length = 20, nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(unique = true, length = 30, nullable = false)
	private String email;
	
	@Column(unique = true, length = 10, nullable = false)
	private String nickname;
	
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	@Enumerated(EnumType.STRING)
	private GradeType grade;
	
	@Enumerated(EnumType.STRING)
	private GenderType gender;
	
	private String userImageUrl;
	
	@Lob
	private String bio;
	
}
