package com.student.persistence;

import java.util.List;

import com.student.domain.ReplyVO;


public interface ReplyDAO {

	public List<ReplyVO> list(String sid);
	
	public void create(ReplyVO replyVO);
	
	public void update(ReplyVO replyVO);
	
	public void delete(int no);
	//추가
	public void deleteRelies(String sid);
}
