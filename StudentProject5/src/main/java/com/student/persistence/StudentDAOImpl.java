package com.student.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.student.domain.StudentVO;

@Component
public class StudentDAOImpl implements StudentDAO {
	
	@Autowired
	private SqlSession sqlSession;

	private static String namespace = "com.student.mapper.StudentMapper";
	
	@Override
	public void create(StudentVO studentVO) {

		sqlSession.insert(namespace + ".create", studentVO);
	}

	@Override
	public StudentVO selectById(String id) {
		
		return sqlSession.selectOne(namespace + ".selectById", id);
	}

	@Override
	public void update(StudentVO studentVO) {
		
		sqlSession.update(namespace + ".update", studentVO);
	}

	@Override
	public void delete(String id) {
		
		sqlSession.delete(namespace + ".delete", id);
	}

	@Override
	public List<StudentVO> selectAll() {
		
		return sqlSession.selectList(namespace + ".selectAll");
	}

}
