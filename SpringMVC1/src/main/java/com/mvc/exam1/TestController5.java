package com.mvc.exam1;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.domain.MemberVO;

@Controller
public class TestController5 {
	
	// doDDDD?id=aaa&name=choi&pw=1111&age=19
	@RequestMapping("/doDDDD")
	public String doDDDD(HttpServletRequest request, Model model) {
		System.out.println("doDDDD 실행됨...");
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		int age = Integer.parseInt(request.getParameter("age"));
		
		MemberVO memberVO = new MemberVO();
		memberVO.setAge(age);
		memberVO.setId(id);
		memberVO.setName(name);
		memberVO.setPw(pw);
		model.addAttribute(memberVO);
		
		return "sub/result3";
	}
	
	// doF?id=aaa&name=choi&pw=1111&age=19
	@RequestMapping("/doF")
	public String doF(MemberVO memberVO, Model model) {
		System.out.println("doF 실행됨...");
		
		model.addAttribute(memberVO);
		
		return "sub/result3";
	}
	
	@RequestMapping("/doF1")
	public String doF1(MemberVO memberVO) {
		System.out.println("/doF1 실행됨...");
		
		//model.addAttribute(memberVO);
		
		return "sub/resutl3";
	}
	
	@RequestMapping("/doF2")
	public String doF2(@ModelAttribute("member")MemberVO memberVO) {
		System.out.println("/doF2 실행됨...");
		
		//model.addAttribute(memberVO);
		
		return "sub/result4";
	}
}
