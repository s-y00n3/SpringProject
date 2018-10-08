package com.mvc.domain;

public class MemberVO {
	private String id;
	private String name;
	private String pw;
	private int age;
	
	public MemberVO() {
		
	}

	public MemberVO(String id, String name, String pw, int age) {
		super();
		this.id = id;
		this.name = name;
		this.pw = pw;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}
