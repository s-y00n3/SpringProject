package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.student.domain.ReplyVO;
import com.student.service.ReplyService;

@RestController
@RequestMapping("/student/replies")
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO replyVO){
		
		ResponseEntity<String> entity = null;
		
		try{
			replyService.addReply(replyVO);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/all/{no}", method=RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("no") String no){
		
		ResponseEntity<List<ReplyVO>> entity = null;
		
		try {
			entity = new ResponseEntity<List<ReplyVO>>(replyService.listReply(no), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<ReplyVO>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/{no}", 
			method={RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("no") int no,
			@RequestBody ReplyVO replyVO){
		
		ResponseEntity<String> entity = null;
		
		try {
			replyVO.setNo(no);
			replyService.modifyReply(replyVO);
			
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/{no}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("no") int no){
		
		ResponseEntity<String> entity = null;
		
		try {
			replyService.removeReply(no);
			
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
