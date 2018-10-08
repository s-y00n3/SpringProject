package com.mvc.exam7;


import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@CookieValue(value="loginCookie", required=false) Cookie cookie,
			Model model) {
//		if(cookie != null && cookie.getValue().equals("ok"))
//			model.addAttribute("loginCookie", "ok");
//		else
//			model.addAttribute("loginCookie", "");
		return "home";
	}
	
	
	
}
