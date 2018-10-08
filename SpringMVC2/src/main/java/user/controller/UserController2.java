package user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import user.domain.LoginDTO;
import user.domain.UserVO;
import user.service.UserService;

//HandlerInterceptorAdapter를 이용한 로그인
@Controller
@RequestMapping("/user")
public class UserController2 {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login2", method=RequestMethod.GET)
	public String loginGet(
			@CookieValue(value="loginCookie", required=false) Cookie cookie,
			@CookieValue(value="loginIdCookie", required=false) Cookie cookie1,
			Model model) {
		
		if(cookie != null && cookie.getValue().equals("ok")) {
			model.addAttribute("loginCookie", "ok");
		}
		if(cookie1 != null) {
			model.addAttribute("loginIdCookie", cookie1.getValue());
		}
		
		return "login2";
		
	}
	
	@RequestMapping(value="loginPost2", method=RequestMethod.POST)
	public String loginPost(LoginDTO loginDTO, Model model) {
		
		UserVO userVO = userService.login(loginDTO);
		
		if(userVO == null) {
			return "redirect:/user/login2";
		}
		
		model.addAttribute("userVO", userVO);
		
		return "loginPost2";
	}
	
	@RequestMapping(value="/otherPage2", method=RequestMethod.GET)
	public String otherPage() {
	
		return "otherPage2";
	}
	
	@RequestMapping(value="/logout2", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login2";
	}
	
	@RequestMapping(value="/authPage1", method=RequestMethod.GET)
	public String authPage1() {
		return "authPage1";
	}
	
	@RequestMapping(value="/authPage2", method=RequestMethod.GET)
	public String authPage2() {
		return "authPage2";
	}
}
