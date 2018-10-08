package com.mvc.exam1;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController4 {
	
	//http://localhost:8080/exam1/doAAAA?id=aaa&pw=111
	@RequestMapping("/doAAAA")
	public String doAAAA(HttpServletRequest req, Model model) {
		System.out.println("doAAAA 실행됨...");
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		
		return "sub/result";
	}
	
	//http://localhost:8080/exam1/doBBBB?id=aaa&pw=111
	@RequestMapping("/doBBBB")
	public String doBBBB(
			@RequestParam("id") String id,
			@RequestParam("pw") int pw,
			Model model) {
		System.out.println("doBBBB 실행됨...");
			
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
			
		return "sub/result2";
	}
}
