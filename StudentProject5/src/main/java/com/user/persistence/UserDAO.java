package com.user.persistence;

import com.user.domain.LoginDTO;
import com.user.domain.UserVO;

public interface UserDAO {
	
		public UserVO login(LoginDTO loginDTO);
}
