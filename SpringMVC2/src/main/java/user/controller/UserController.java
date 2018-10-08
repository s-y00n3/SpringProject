package user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginGet(
			@CookieValue(value="loginCookie", required=false) Cookie cookie,
			@CookieValue(value="loginIdCookie", required=false) Cookie cookie1,
			Model model
			) {
		
		if(cookie != null && cookie.getValue().equals("ok")) {
			model.addAttribute("loginCookie", "ok");
		}
		if(cookie != null) {
			model.addAttribute("loginIdCookie", cookie1.getValue());
		}
		
		return "login";
	}
	
	@RequestMapping(value="/loginPost", method=RequestMethod.POST)
	public String loginPost(LoginDTO loginDTO,
			HttpSession session, Model model,
			HttpServletResponse response,
			@CookieValue(value="loginCookie", required=false) Cookie cookie,
			@CookieValue(value="loginIdCookie", required=false) Cookie cookie1
			) {
		
		//userService에게 login처리 위임
		//사용자가 로그인하기 위해 입력한 정보가 맞으면
		//DB에서 로그인한 정보와 일치하는 레코드를 select하여 userVO가 리턴됨
		//DB에 해당 레코드가 없으므로 null값인 userVO 가 리턴됨
		UserVO userVO = userService.login(loginDTO);
		
		//사용자 로그인 정보가 틀리면 다시 로그인 할 수 있도록 login으로 리다이렉트함
		if(userVO == null) {
			return "redirect:/user/login";
		}
		
		model.addAttribute("userVO", userVO);
		session.setAttribute("login", userVO);
		
		//사용자가 로그인하면서 아이디기억하기를 체크했으면
		if(loginDTO.isUseCookie()) {
			//기존에 쿠키가 없을 경우
			//새로운 쿠키를 생성해서 사용자의 브라우저에 저장함
			if(cookie == null) {
				Cookie loginCookie = new Cookie("loginCookie", "ok");
				//쿠키 만료시간 7일로 세팅
				loginCookie.setMaxAge(60 * 60 * 24 * 7);
				//사용자 브라우저로 쿠키를 보내 저장하기
				response.addCookie(loginCookie);
				
				Cookie loginIdCookie
				= new Cookie("loginIdCookie", userVO.getId());
				loginIdCookie.setMaxAge(60 * 60 * 24 * 7);
				response.addCookie(loginIdCookie);
			}	
		}
		
		//사용자가 로그인하면서 아이디기억하기 체크를 해제했으면
		else {
			//기존의 쿠키가 남아있을 경우, 사용자가 아이디기억하기를 원하지 않으므로
			//기존의 쿠키를 삭제해야함
			if(cookie != null) {
				//쿠키가 삭제됨
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				
				cookie1.setMaxAge(0);
				response.addCookie(cookie1);
			}
		}
		
		return "loginPost";
	}
	
	@RequestMapping(value="otherPage", method=RequestMethod.GET)
	public String otherPage() {
		return "otherPage";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/user/login";
	}
	
	
}

