package user.persistence;

import user.domain.LoginDTO;
import user.domain.UserVO;

public interface UserDAO {
	public UserVO login(LoginDTO loginDTO);
}
