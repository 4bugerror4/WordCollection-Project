package com.word.collection.entity.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.word.collection.entity.UserAccount;
import com.word.collection.entity.enums.GenderType;
import com.word.collection.entity.enums.GradeType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountModifyDto {
	
	private Long id;
	
	@NotBlank
	@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
	private String nickname;
	
	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String password;
	
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
	private String email;
	
	private GradeType grade;
	private GenderType gender;
	private String bio;
	
	public UserAccount toEntity() {
		return UserAccount.builder()
				.id(id)
				.nickname(nickname)
				.password(password)
				.email(email)
				.grade(grade)
				.gender(gender)
				.bio(bio)
				.build();
	}

}
