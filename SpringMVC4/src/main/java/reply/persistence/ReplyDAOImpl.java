package reply.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import reply.domain.ReplyVO;

@Repository //@Component
public class ReplyDAOImpl implements ReplyDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static String namespace
	= "reply.mapper.ReplyMapper";

	@Override
	public List<ReplyVO> list(String sid) {
		
		
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

}
