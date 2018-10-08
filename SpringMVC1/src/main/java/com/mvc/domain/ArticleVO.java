package com.mvc.domain;

public class ArticleVO {

	private String title;
	private String content;
	
	public ArticleVO() {
		
	}

	public ArticleVO(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ArticleVO [title=" + title + ", content=" + content + "]";
	}
	
	
	
}
