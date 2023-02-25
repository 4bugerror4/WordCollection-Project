package com.word.collection.serivce;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.word.collection.entity.UserAccount;

public interface UserAccountService {
	
	UserAccount findById(Long id);
	UserAccount joinUser(UserAccount userAccount);
	UserAccount findUsername(String username);
	UserAccount modifyUser(UserAccount userAccount, MultipartFile file);
	void checkUsernameDuplicate(UserAccount userAccount);
	void checkNicknameDuplicate(UserAccount userAccount);
	void checkEmailDuplicate(UserAccount userAccount);
	
	List<UserAccount> findUserAll();

}
