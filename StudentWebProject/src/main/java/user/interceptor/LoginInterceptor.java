package user.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import user.domain.UserVO;

//어떤 요청을 중간에 가로채서 어떤 작업을 대신해줌
public class LoginInterceptor extends HandlerInterceptorAdapter{

	//어떤 요청이 처리되기 전에
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") != null) {
			System.out.println("이전의 로그인 정보 지우기");
			session.removeAttribute("login");
		}
		
		return true;
	}

	//어떤 요청이 처리된 후에
	//우리 예제에서는 loginPost2 요청의 loginPost()메소드안의 내용을 다 실행하고
	//loginPost2.jsp 페이지 보여주기 전에 아래 postHandle처리를 수행한다
	//전체 흐름을 다시 정리하면
	//어딘가에서 loginPost2요청하면
	//위의 preHandle()메소드 실행하고, loginController2의 loginPost()메소드 실행하고,
	//아래 postHandler()메소드 실행하고 마지막에 loginPost2.jsp페이지 보여줌
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		UserVO userVO = (UserVO)modelMap.get("userVO");
		
		if(userVO != null) {
			System.out.println("로그인 성공....");
			session.setAttribute("login", userVO);
			
			//사용자가 로그인아이디 기억을 체크했을 경우
			if(request.getParameter("useCookie") != null) {
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
			//사용자가 로그인아이디 기억을 체크하지 않았을 경우
			//기존의 쿠키값 지우기
			else {
				Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
				Cookie loginIdCookie = WebUtils.getCookie(request, "loginIdCookie");
				
				if(loginCookie != null) {
					loginCookie.setMaxAge(0);
					response.addCookie(loginCookie);
					
					loginIdCookie.setMaxAge(0);
					response.addCookie(loginIdCookie);
				}
			}
		}
		
		//AuthInterceptor 생성후 추가할 부분
		Object dest = session.getAttribute("dest");
		
		response.sendRedirect(dest != null ? (String)dest :  "../student/list");
	}
	
	
}
