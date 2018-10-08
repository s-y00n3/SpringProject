package com.user.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.student.domain.ReplyVO;
import com.user.domain.LoginDTO;
import com.user.domain.UserVO;
import com.user.service.UserService;



// HandlerInterceptorAdapter를 이용한 로그인
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginGet(
			@CookieValue(value="loginCookie", required=false) Cookie cookie,			
			@CookieValue(value="loginIdCookie", required=false) Cookie cookie1,
			Model model){
		
		if(cookie != null && cookie.getValue().equals("ok"))
			model.addAttribute("loginCookie", "ok");
		
		if(cookie1 != null)
			model.addAttribute("loginIdCookie", cookie1.getValue());
		
		return "user/login";
	}
	
	@RequestMapping(value="/loginPost", method=RequestMethod.POST)
	public String loginPost(LoginDTO loginDTO, Model model){
		
		UserVO userVO = userService.login(loginDTO);
		
		if(userVO == null){
			return "redirect:/user/login";
		}
		model.addAttribute("userVO", userVO);
		
		return "student/list";
	}	
	
	@RequestMapping(value="/loginAjax", method=RequestMethod.POST)
	public ResponseEntity<String> loginAjax(
			@RequestBody LoginDTO loginDTO, HttpSession session,
			@CookieValue(value="loginCookie", required=false) Cookie cookie,
			@CookieValue(value="loginIdCookie", required=false) Cookie cookie1,
			HttpServletResponse response){
		
		ResponseEntity<String> entity = null;
		UserVO userVO = null;		
		
		try {
			userVO = userService.login(loginDTO);
		
			if(userVO == null){
				entity = new ResponseEntity<String>("FAIL",HttpStatus.OK);
			}else {
				session.setAttribute("login", userVO);
				if(loginDTO.isUseCookie()){
					if(cookie == null){
						System.out.println("쿠키생성");
						Cookie loginCookie = new Cookie("loginCookie", "ok");
						loginCookie.setMaxAge(60 * 60 * 24 * 7);
						loginCookie.setPath("/");
						response.addCookie(loginCookie);
					}		
					if(cookie1 == null) {	
						System.out.println("쿠키생성1");
						Cookie loginIdCookie
						    = new Cookie("loginIdCookie", userVO.getId());
						loginIdCookie.setMaxAge(60 * 60 * 24 * 7);
						loginIdCookie.setPath("/");
						response.addCookie(loginIdCookie);
					}
				}else{			
					if(cookie != null){
						cookie = new Cookie("loginCookie", "");
						cookie.setPath("/");
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
					if(cookie1 != null) {
						cookie1 = new Cookie("loginIdCookie", "");
						cookie1.setPath("/");
						cookie1.setMaxAge(0);
						response.addCookie(cookie1);
					}
				}
				
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}	
	
	@RequestMapping(value="/logoutAjax", method=RequestMethod.POST)
	public ResponseEntity<String> logoutAjax(HttpSession session){
		ResponseEntity<String> entity = null;
		
		try {
			session.invalidate();
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);			
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session){
		
		session.invalidate();
		return "redirect:/user/login";
	}
}
