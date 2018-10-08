package reply.service;

import java.util.List;

import reply.domain.ReplyVO;

public interface ReplyService {

	public void addReply(ReplyVO replyVO);
	
	public List<ReplyVO> listReply(String sid);
	
	public void modifyReply(ReplyVO replyVO);
	
	public void removeReply(int no);
}
