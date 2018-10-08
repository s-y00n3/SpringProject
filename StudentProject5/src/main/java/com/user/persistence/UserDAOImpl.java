package com.user.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.user.domain.LoginDTO;
import com.user.domain.UserVO;


@Component
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private static String namespace = "com.mvc.mapper.UserMapper";
	
	@Override
	public UserVO login(LoginDTO loginDTO) {
		
		UserVO user = sqlSession.selectOne(namespace + ".login", loginDTO);
		return user;
	}

}
