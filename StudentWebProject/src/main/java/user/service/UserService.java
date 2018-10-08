package user.service;

import user.domain.LoginDTO;

import user.domain.UserVO;

public interface UserService {

	public UserVO login(LoginDTO loginDTO);
}
