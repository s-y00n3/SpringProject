package user.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import user.domain.LoginDTO;
import user.domain.UserVO;

@Repository //@Component
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private static String namespace = "user.mapper.UserMapper";
	
	
	@Override
	public UserVO login(LoginDTO loginDTO) {
		
		UserVO user = sqlSession.selectOne(namespace + ".login", loginDTO);
		return user;
	}

}
