package com.user.service;

import com.user.domain.LoginDTO;
import com.user.domain.UserVO;

public interface UserService {

	public UserVO login(LoginDTO loginDTO);
	
	
}
