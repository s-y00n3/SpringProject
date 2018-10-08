package reply.persistence;

import java.util.List;

import reply.domain.ReplyVO;

public interface ReplyDAO {
	
	public List<ReplyVO> list(String sid);
	
	public void create(ReplyVO replyVO);
	
	public void update(ReplyVO replyVO);
	
	public void delete(int no);
}
