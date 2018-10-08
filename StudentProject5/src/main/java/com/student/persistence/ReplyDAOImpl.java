package com.student.persistence;

import java.util.List;

import javax.naming.NameParser;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.student.domain.ReplyVO;



@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private static String namespace = "com.rest.mapper.ReplyMapper";
	
	@Override
	public List<ReplyVO> list(String sid)  {
		
		return sqlSession.selectList(namespace + ".list", sid);
	}

	@Override
	public void create(ReplyVO replyVO) {

		sqlSession.insert(namespace + ".create", replyVO);		
	}

	@Override
	public void update(ReplyVO replyVO) {

		sqlSession.update(namespace + ".update", replyVO);
	}

	@Override
	public void delete(int no) {

		sqlSession.delete(namespace + ".delete", no);
	}

	@Override
	public void deleteRelies(String sid) {
		sqlSession.delete(namespace + ".deleteReplies", sid);
		
	}

	
	

}
