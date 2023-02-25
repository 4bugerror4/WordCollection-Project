package com.word.collection.serivce;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.word.collection.entity.UserAccount;
import com.word.collection.entity.enums.RoleType;
import com.word.collection.handler.ex.CustomUserCheckApiException;
import com.word.collection.repository.UserAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

	private final UserAccountRepository userAccountRepository;
	private final BCryptPasswordEncoder encoder;

	@Value("${file.path}")
	private String uploadFolder;

	@Transactional
	@Override
	public UserAccount joinUser(UserAccount userAccount) {

		String rawPassword = userAccount.getPassword();
		String encPassword = encoder.encode(rawPassword);
		userAccount.setPassword(encPassword);
		userAccount.setRole(RoleType.ROLE_USER);

		UserAccount userEntity = userAccountRepository.save(userAccount);

		return userEntity;
	}

	@Transactional(readOnly = true)
	@Override
	public UserAccount findUsername(String username) {

		UserAccount user = userAccountRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("해당 유저아이디는 찾지 못했습니다."));

		return user;
	}

	@Transactional
	@Override
	public UserAccount modifyUser(UserAccount userAccount, MultipartFile file) {

		UserAccount user = userAccountRepository.findById(userAccount.getId())
				.orElseThrow(() -> new IllegalArgumentException("해당 번호의 유저는 없습니다."));

		String rawPassword = userAccount.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setEmail(userAccount.getEmail());
		user.setNickname(userAccount.getNickname());
		user.setGender(userAccount.getGender());
		user.setBio(userAccount.getBio());
		user.setGrade(userAccount.getGrade());

		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid + "_" + file.getOriginalFilename();

		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		
		if (file.isEmpty()) {
			
			if (user.getUserImageUrl() != null) {
				user.setUserImageUrl(user.getUserImageUrl());
			} else if (user.getUserImageUrl() == null) {
				user.setUserImageUrl(uploadFolder + "NoImage.png");
			}
			
		} else {
			try {
				Files.write(imageFilePath, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}

			user.setUserImageUrl(imageFileName);
		}

		return user;
	}

	@Transactional(readOnly = true)
	@Override
	public void checkUsernameDuplicate(UserAccount userAccount) {

		boolean usernameDuplicate = userAccountRepository.existsByUsername(userAccount.getUsername());
		
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("입력하시 아이디는 이미 존재합니다.", userAccount.getUsername());
		
		if (usernameDuplicate) {
			throw new CustomUserCheckApiException("이미 존재하는 아이디 입니다.", errorMap);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public void checkNicknameDuplicate(UserAccount userAccount) {

		boolean nicknameDuplicate = userAccountRepository.existsByNickname(userAccount.getNickname());
		
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("입력하시 닉네임은 이미 존재합니다.", userAccount.getNickname());

		if (nicknameDuplicate) {
			throw new CustomUserCheckApiException("이미 존재하는 닉네임 입니다.", errorMap);
		}

	}

	@Transactional(readOnly = true)
	@Override
	public void checkEmailDuplicate(UserAccount userAccount) {

		boolean emailDuplicate = userAccountRepository.existsByEmail(userAccount.getEmail());
		
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("입력하시 이메일은 이미 존재합니다.", userAccount.getEmail());


		if (emailDuplicate) {
			throw new CustomUserCheckApiException("이미 존재하는 이메일 입니다.", errorMap);
		}

	}

	@Transactional(readOnly = true)
	@Override
	public List<UserAccount> findUserAll() {

		List<UserAccount> users = userAccountRepository.findAll();
		
		return users;
	}

	@Override
	public UserAccount findById(Long id) {
		
		UserAccount user = userAccountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 번호의 유저는 존재하지 않습니다."));

		return user;
	}

}
