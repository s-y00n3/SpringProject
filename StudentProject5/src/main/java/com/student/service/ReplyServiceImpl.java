package com.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.domain.ReplyVO;
import com.student.persistence.ReplyDAO;


@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDAO replyDAO;
	
	@Override
	public void addReply(ReplyVO replyVO) {
		
		replyDAO.create(replyVO);
	}

	@Override
	public List<ReplyVO> listReply(String sid) {

		return replyDAO.list(sid);
	}
	
	@Override
	public void modifyReply(ReplyVO replyVO) {
		
		replyDAO.update(replyVO);
	}

	
	@Override
	public void removeReply(int no) {		
		replyDAO.delete(no);
	}

}
