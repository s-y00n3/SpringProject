package user.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//로그인한 사용자만 접근할 수 있는 서비스 처리
public class AuthInterceptor extends HandlerInterceptorAdapter{

	//사용자가 요청한 페이지 정보(목적지)를 추출함
	private void saveDest(HttpServletRequest req) {
		
		String uri = req.getRequestURI();
		String query = req.getQueryString();
		
		if(query == null || query.equals("null")) {
			query = "";
		}else {
			query = "?" + query;
		}
		
		if(req.getMethod().equals("GET")) {
			req.getSession().setAttribute("dest", uri + query);
		}else if(req.getMethod().equals("POST")) {
			req.getSession().setAttribute("dest", uri);
		}
		
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		//로그인하지 않았으면 
		if(session.getAttribute("login") == null) {
			saveDest(request);
			response.sendRedirect("../user/login");
			
			return false;
		}
		return true;
	}

}
