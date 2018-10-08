package user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.domain.LoginDTO;
import user.domain.UserVO;
import user.persistence.UserDAO;

@Service //@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserVO login(LoginDTO loginDTO) {
		UserVO user = userDAO.login(loginDTO);
		return user;
	}

}
