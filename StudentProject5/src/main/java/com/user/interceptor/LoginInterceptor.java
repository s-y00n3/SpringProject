package com.user.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.user.domain.UserVO;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		Object userVO = modelMap.get("userVO");
		
		if(userVO != null){
			System.out.println("로그인 성공...");
			session.setAttribute("login", userVO);
			
			// 쿠키 처리부분
			if(request.getParameter("useCookie") != null){
				// 쿠키 생성부분
				Cookie loginCookie = new Cookie("loginCookie", "ok");
				loginCookie.setMaxAge(60 * 60 * 24 * 7);
				response.addCookie(loginCookie);
				
				Cookie loginIdCookie
		= new Cookie("loginIdCookie", ((UserVO)userVO).getId());
				loginIdCookie.setMaxAge(60 * 60 * 24 * 7);
				response.addCookie(loginIdCookie);
				
			}else{
				//쿠키삭제부분
				Cookie loginCookie 
				= WebUtils.getCookie(request, "loginCookie");
				if(loginCookie != null){
					//loginCookie.setPath("/");
					loginCookie.setMaxAge(0);
					response.addCookie(loginCookie);
				}
				
				Cookie loginIdCookie = 
					WebUtils.getCookie(request, "loginIdCookie");
				if(loginIdCookie != null){
					//loginCookie.setPath("/");
					loginIdCookie.setMaxAge(0);
					response.addCookie(loginIdCookie);
				}
			}
			
			// AuthInterceptor 생성후 추가할 부분
			// 세션에서 로그인후에 가야할 페이지 정보인 "dest"정보를 가져와서
			// 페이지를 이동시킴
			Object dest = session.getAttribute("dest");
			
			response.sendRedirect(dest != null ? (String)dest : "../student/list");
		
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") != null){
			System.out.println("로그인 정보 지우기...");
			session.removeAttribute("login");
		}
		return true;
	}
	
	
}
