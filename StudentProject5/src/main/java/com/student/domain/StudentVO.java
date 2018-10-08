package com.student.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

/*
create table students(	
	no int not null,
	id varchar(10) not null primary key,
    name varchar(30) not null,
    password varchar(20) not null,
    savedname varchar(100),
    email varchar(50) not null,
    regdate datetime not null default now()
);

 */
public class StudentVO {
	private int no;
	private String id;
	private String name;
	private String password;
	private String email;
	private Date regdate;
	private MultipartFile file;
	private String savedName;
	
	public StudentVO() {
	}
	
	public StudentVO(int no, String id, String name, String password, String email, Date regdate, MultipartFile file,
			String savedName) {
		super();
		this.no = no;
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.regdate = regdate;
		this.file = file;
		this.savedName = savedName;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getSavedName() {
		return savedName;
	}

	public void setSavedName(String savedName) {
		this.savedName = savedName;
	}

	@Override
	public String toString() {
		return "StudentVO [no=" + no + ", id=" + id + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", regdate=" + regdate + ", file=" + file + ", savedName=" + savedName + "]";
	}

	
	
}
