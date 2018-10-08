package com.student.domain;

import java.util.Date;

/*create table reply(
no int auto_increment not null primary key,
sid varchar(10) not null,
replytext varchar(300) not null,
replyer varchar(30) not null,
regdate datetime not null default now()
);*/
public class ReplyVO {

	private int no;
	private String sid;
	private String replyText;
	private String replyer;
	private Date regdate;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getReplyText() {
		return replyText;
	}
	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "ReplyVO [no=" + no + ", sid=" + sid + ", replyText=" + replyText + ", replyer=" + replyer + ", regdate="
				+ regdate + "]";
	}	
}
