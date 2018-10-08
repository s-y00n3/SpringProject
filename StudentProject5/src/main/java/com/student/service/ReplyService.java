package com.student.service;

import java.util.List;

import com.student.domain.ReplyVO;



public interface ReplyService {
	public void addReply(ReplyVO replyVO) throws Exception;
	public List<ReplyVO> listReply(String sid) throws Exception;
	public void modifyReply(ReplyVO replyVO) throws Exception;
	public void removeReply(int no) throws Exception;
}
