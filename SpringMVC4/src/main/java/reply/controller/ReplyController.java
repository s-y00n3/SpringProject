package reply.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import reply.domain.ReplyVO;
import reply.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {

	//REST방식
	// - URI는 정보의 자원을 표현함
	// - 자원에 대한 행위는 HTTP메소드(GET, POST, PUT, DELETE)등으로 표현함
	// - 주고 받는 데이터는 XML, JSON타입으로
	// /replies/all/123		GET방식 : 게시물 123번의 모든 댓글 리스트를 조회함
	// /replies/ + 데이터 		POST방식 : 새로운 댓글 추가
	// /replies/456 + 데이터 	PUT/PATCH방식 : 456 댓글의 수정
	// /replies/456			DELETE방식 : 456댓글 삭제
	
	@Autowired
	private ReplyService replyService;
	
	//댓글 등록
	//@RequestBody : 전송된 JSON데이터를 객체로 변환시켜주는 어노테이션
	//http://localhost:8080/web/replies
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> register(
			@RequestBody ReplyVO replyVO
			){
		
		ResponseEntity<String> entity = null;
		
		try {
			replyService.addReply(replyVO);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
	}
	
	//해당번호의 전체 댓글 목록 가져오기
	//@PathVariable : URI의 경로에서 원하는 데이터를 추출하는 용도로 사용
	//http://localhost:8080/web/replies/all/1
	
	@RequestMapping(value="/all/{no}", method=RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(
			@PathVariable("no") String no){
		
		ResponseEntity<List<ReplyVO>> entity = null;
		
		try {
			entity
			= new ResponseEntity<List<ReplyVO>>(
					replyService.listReply(no), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity
			= new ResponseEntity<List<ReplyVO>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	//댓글 수정
	//http://localhost:8080/web/replies/123
	@RequestMapping(value="/{no}", method= {RequestMethod.PATCH, RequestMethod.PUT})
	public ResponseEntity<String> update(
			@PathVariable("no") int no,
			@RequestBody ReplyVO replyVO){
		
		ResponseEntity<String> entity = null;
		
		try {
			replyVO.setNo(no);
			replyService.modifyReply(replyVO);
			
			entity
			= new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			entity
			= new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
	}
	
	//댓글 삭제
	//http://localhost:8080/replies/123
	@RequestMapping(value="/{no}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("no") int no){
		
		ResponseEntity<String> entity = null;
		
		try {
			
			replyService.removeReply(no);
			entity
			= new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity
			= new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
