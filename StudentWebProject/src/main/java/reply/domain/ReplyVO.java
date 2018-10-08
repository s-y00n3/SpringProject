package reply.domain;

import java.util.Date;

public class ReplyVO {
	private int no;
	private String sid;
	private String replyText;
	private String replyer;
	private Date regdate;
	
	public ReplyVO() {
	}

	public ReplyVO(int no, String sid, String replyText, String replyer, Date regdate) {
		super();
		this.no = no;
		this.sid = sid;
		this.replyText = replyText;
		this.replyer = replyer;
		this.regdate = regdate;
	}

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
