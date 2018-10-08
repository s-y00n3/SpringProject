package com.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mvc.domain.ArticleVO;
import com.mvc.service.ArticleService;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(value="/article", method=RequestMethod.GET)
	public String articleGet() {
		System.out.println("articleGet 실행됨...");
		
		return "article/form";
	}
	
	@RequestMapping(value="/article", method=RequestMethod.POST)
	public String articlePost(ArticleVO articleVO) {
		System.out.println("articlePost 실행됨...");
		
		articleService.writeArticle(articleVO);
		
		return "article/view";
	}
}
