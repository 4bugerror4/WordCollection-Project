package com.word.collection.entity.dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.word.collection.entity.UserAccount;
import com.word.collection.entity.enums.GradeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountJoinDto {

	@Size(min = 2, max = 20)
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	@NotBlank
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
	private String email;
	
	@NotBlank
	@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
	private String nickname;
	
	private GradeType grade;
	
	public UserAccount toEntity() {
		return UserAccount.builder()
				.username(username)
				.password(password)
				.email(email)
				.nickname(nickname)
				.grade(grade)
				.build();
	}

}
