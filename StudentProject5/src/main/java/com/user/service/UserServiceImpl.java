package com.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.user.domain.LoginDTO;
import com.user.domain.UserVO;
import com.user.persistence.UserDAO;



@Component
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserVO login(LoginDTO loginDTO) {
		
		UserVO user = userDAO.login(loginDTO);
		return user;
	}
	

}
